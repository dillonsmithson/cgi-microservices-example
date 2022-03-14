package com.cgi.glk.ectp.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Properties {

    @Value("${com.cgi.glk.ectp.common.jwt-secret}")
    @Getter
    private String jwtSecret;
}