package com.cgi.glk.ectp.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cgi.glk.ectp.common.Properties;
import com.cgi.glk.ectp.common.dto.CredentialDTO;
import com.cgi.glk.ectp.common.service.AuthService;
import com.cgi.glk.ectp.auth.model.JWTModel;
import com.cgi.glk.ectp.auth.repository.JWTRepository;
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

    private static Map<String, String> credentials = Map.of("Jeff",     "jefe",
                                                            "Alan",     "nate",
                                                            "Ashley",   "ash" );

    public Optional<String> forge(final CredentialDTO pCredentialDTO) {
        log.info("in JWTService - forge");
        val _password = Optional.ofNullable(credentials.get(pCredentialDTO.getUsername()));
        if (_password.isEmpty()) {
            return Optional.empty();
        }

        if (!_password.get().equals(pCredentialDTO.getPassword())) {
            return Optional.empty();
        }

        val jwt = JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withExpiresAt(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
                .withIssuer("com.cgi.glk.ectp")
                .withClaim("username", pCredentialDTO.getUsername())
                .withClaim("ownerPriv", "CRUD")
                .sign(Algorithm.HMAC256(properties.getJwtSecret()));

        try {
            jwtRepository.deleteById(pCredentialDTO.getUsername()); //deletes old JWT
        } catch (final Exception e) {
            log.info("When deleting", e);
        }
        jwtRepository.save(JWTModel.of(pCredentialDTO.getUsername(), jwt)); //save new one

        return Optional.of(jwt);
    }

    public boolean validate(final String pJWT) {
        log.info("in JWTService - validate");
        log.info("Validating JWT {}", pJWT);
        if (!authService.validate(pJWT)) {
            return false;
        }

        return jwtRepository.byHash(pJWT.hashCode()).stream()
                .map(m -> m.getJwt())
                .anyMatch(j -> j.equals(pJWT));
    }

    public void invalidate(final String pJWT) {
        log.info("in JWTService - invalidate");
        if (validate(pJWT)) {
            try {
                jwtRepository.byHash(pJWT.hashCode()).stream()
                        .filter(m -> m.getJwt().equals(pJWT))
                        .map(m -> m.getUsername())
                        .forEach(u -> jwtRepository.deleteById(u));
            } catch (final Exception e) {
                log.info("When deleting", e);
            }
        }
    }
/*
    private boolean verify(final String pJWT) {
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

 */
}
