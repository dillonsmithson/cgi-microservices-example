package com.cgi.glk.ectp.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {

    @Value("${com.cgi.glk.ectp.auth.jwt-secret}")
    @Getter
    private String secret;
}
