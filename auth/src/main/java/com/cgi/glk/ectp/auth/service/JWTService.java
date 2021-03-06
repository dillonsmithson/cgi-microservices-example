package com.cgi.glk.ectp.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cgi.glk.ectp.auth.model.JWTModel;
import com.cgi.glk.ectp.auth.repository.JWTRepository;
import com.cgi.glk.ectp.common.Properties;
import com.cgi.glk.ectp.common.dto.CredentialDTO;
import com.cgi.glk.ectp.common.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class JWTService {
    @Autowired private Properties properties;
    @Autowired private JWTRepository jwtRepository;
    @Autowired private AuthService authService;

    private final Map<String, String> credentials = Map.of( "Jeff",     "jefe",
                                                            "Alan",     "nate",
                                                            "Ashley",   "ash");

    public Optional<String> forge(final CredentialDTO pCredentialDTO) {
        val _password = Optional.ofNullable(credentials.get(pCredentialDTO.getUsername()));

        if (_password.isEmpty()) {
            log.info("No password found");
            return Optional.empty();
        }

        if(!_password.get().equals(pCredentialDTO.getPassword())) {
            log.info("Password doesn't match");
            return Optional.empty();
        }

        log.info("Creating new JWT...");
        val jwt = JWT.create()
                    .withIssuer("com.cgi.glk.ectp")
                    .withClaim("ownerPriv", "CRUD")
                    .withClaim("username", pCredentialDTO.getUsername())
                    .withExpiresAt(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
                    .withJWTId(UUID.randomUUID().toString())
                    .sign(Algorithm.HMAC256(properties.getJwtSecret()));
        try {
            log.info("Looking for existing JWT in database");
            jwtRepository.deleteById(pCredentialDTO.getUsername());
        } catch (final Exception e) {
            log.info("When deleting", e);
        }

        log.info("Sending JWT to database.");
        jwtRepository.save(JWTModel.of(pCredentialDTO.getUsername(), jwt));

        return Optional.ofNullable(jwt); // This may change.
    }

    public boolean validate(final String pJWT) {
        if (!verify(pJWT)){
            log.warn("Could not verify JWT");
            return false;
        }

        log.info("Validating JWT");
        return jwtRepository.byHash(pJWT.hashCode()).stream()
                .map(m -> m.getJwt())
                .anyMatch(j -> j.equals(pJWT));
    }

    public void delete(final String pJWT) {
        if (validate(pJWT)) {
            try {
                log.info("Attempting to delete JWT entry");
                jwtRepository.byHash(pJWT.hashCode()).stream()
                        .filter(m -> m.getJwt().equals(pJWT))
                        .map(m -> m.getUsername())
                        .forEach(u -> jwtRepository.deleteById(u));
            } catch (final Exception e) {
                log.info("When deleting", e);
            }
        }
    }

    private boolean verify(final String pJWT) {
        log.info("Attempting to verify signature of JWT");
        val verifier = JWT.require(Algorithm.HMAC256(properties.getJwtSecret()))
                .withIssuer("com.cgi.glk.ectp")
                .build();

        try {
            verifier.verify(pJWT);
        } catch (final JWTVerificationException e) {
            log.warn("Invalid JWT", e);
            return false;
        }

        return true;
    }
}
