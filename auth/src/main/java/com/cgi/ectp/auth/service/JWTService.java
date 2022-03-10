package com.cgi.ectp.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cgi.ectp.auth.Properties;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class JWTService {

    @Autowired private Properties properties;

    public String forge() {
        return JWT.create()
                .withIssuer("com.cgi.ectp")
                .withClaim("ownerPriv", "CRUD")
                .withJWTId(UUID.randomUUID().toString())
                .sign(Algorithm.HMAC256(properties.getJwtSecret()));
    }

    public boolean validate(final String pJWT) {
        val verifier = JWT.require(Algorithm.HMAC256(properties.getJwtSecret()))
                .withIssuer("com.cgi.ectp")
                .build();

        try {
            verifier.verify(pJWT);
        } catch(final JWTVerificationException e) {
            log.warn("Invalid JWT", e);
        }

        return true;
    }
}
