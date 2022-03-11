package com.cgi.glk.ectp.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cgi.glk.ectp.auth.Properties;
import com.cgi.glk.ectp.auth.dto.CredentialDTO;
import com.cgi.glk.ectp.auth.model.JWTModel;
import com.cgi.glk.ectp.auth.repository.JWTRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class JWTService {
    @Autowired private Properties properties;
    @Autowired private JWTRepository jwtRepository;

    private final Map<String, String> credentials = Map.of( "Jeff",     "jefe",
                                                            "Alan",     "nate",
                                                            "Ashley",   "ash");

    public Optional<String> forge(final CredentialDTO pCredentialDTO) {
        if (!credentials.containsKey(pCredentialDTO.getUsername())) {
            return Optional.empty();
        }
        if (!credentials.get(pCredentialDTO.getUsername()).equals(pCredentialDTO.getPassword())) {
            return Optional.empty();
        }

        val jwt = JWT.create()
                    .withIssuer("com.cgi.glk.ectp")
                    .withClaim("ownerPriv", "CRUD")
                    .withJWTId(UUID.randomUUID().toString())
                    .sign(Algorithm.HMAC256(properties.getSecret()));
        try {
            jwtRepository.deleteById(pCredentialDTO.getUsername());
        } catch (final Exception e) {
            log.info("When deleting", e);
        }
        jwtRepository.save(JWTModel.of(pCredentialDTO.getUsername(), jwt));

        return Optional.ofNullable(jwt); // This may change. 
    }

    public boolean validate(final String pJWT) {
        if (!verify(pJWT)){
            return false;
        }

        return jwtRepository.byHash(pJWT.hashCode()).stream()
                .map(m -> m.getJwt())
                .anyMatch(j -> j.equals(pJWT));
    }

    public void delete(final String pJWT) {
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

    private boolean verify(final String pJWT) {
        val verifier = JWT.require(Algorithm.HMAC256(properties.getSecret()))
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
