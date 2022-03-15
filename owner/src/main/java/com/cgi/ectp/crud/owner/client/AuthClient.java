package com.cgi.ectp.crud.owner.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authClient", url = "${com.cgi.ectp.orch.authURL}")
public interface AuthClient {

    @PostMapping("/authenticate")
    String authenticate();

    @PostMapping("/validate")
    boolean validate(@RequestBody final String pJWT);

    @PostMapping("/logout")
    void logout(@RequestBody final String pJWT);

}
