package com.cgi.ectp.crud.owner.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authClient", url = "${com.cgi.ectp.orc.authURL}")
public interface AuthClient {

    @PostMapping("/authenticate")
    public String authenticate();

    @PostMapping("/validate")
    public boolean validate(@RequestBody final String pJWT);

    @PostMapping("/logout")
    public void logout(@RequestBody final String pJWL);
}