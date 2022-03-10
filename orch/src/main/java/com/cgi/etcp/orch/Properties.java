package com.cgi.etcp.orch;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {

    @Value("${com.cgi.etcp.orch.ownerURL}")
    @Getter
    private String ownerURL;

    @Value("${com.cgi.etcp.crud.owner.secret}")
    @Getter
    private String secret;
}
