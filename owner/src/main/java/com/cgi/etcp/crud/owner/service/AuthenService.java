package com.cgi.etcp.crud.owner.service;

import com.cgi.etcp.crud.owner.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenService {

    @Autowired private Properties properties;

    public boolean checkAuthen(final String pToken) {
        return properties.getSecret().equals(parseToken(pToken));
    }

    private final String BEARER = "Bearer ";

    private String parseToken(final String pToken) {
        return (pToken.startsWith(BEARER) ? pToken.substring(BEARER.length()) : pToken);
    }
}
