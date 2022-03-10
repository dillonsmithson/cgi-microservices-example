package com.cgi.ectp.crud.owner.service;

import com.cgi.ectp.crud.owner.Properties;
import com.cgi.ectp.crud.owner.client.AuthClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthService {

    @Autowired private Properties properties;
    @Autowired private AuthClient authClient;

    public boolean validateToken(final String pToken) {
        log.info("Validating token {}", pToken);
        return authClient.validate(parseToken(pToken));
    }

    private final String BEARER = "Bearer ";

    private String parseToken(final String pToken) {
        return (pToken.startsWith(BEARER)) ? pToken.substring(BEARER.length()) : pToken;
    }
}
