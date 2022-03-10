package com.cgi.ectp.orch;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Properties {

    @Value("${com.cgi.ectp.orch.ownerURL}")
    private String ownerURL;

    @Value("${com.cgi.ectp.crud.owner.jwt-secret}")
    @Getter
    private String jwtSecret;
}