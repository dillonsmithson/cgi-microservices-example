package com.cgi.glk.ectp.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Config {
    @Autowired private Properties properties;

    @Bean
    public JWTVerifier jwtVerifier() {
        log.info("in Config in library - jwtVerifier");
        return JWT.require(Algorithm.HMAC256(properties.getJwtSecret()))
                .build();

    }

}
