package com.cgi.glk.ectp.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class Config {

    @Autowired private Properties properties;

    @Bean
    public JWTVerifier jwtVerifier() {
        return JWT.require(Algorithm.HMAC256(properties.getJwtSecret())).build();
    }
}
