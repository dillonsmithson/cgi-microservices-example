package com.cgi.glk.ectp.common.client;

import com.cgi.glk.ectp.common.dto.CredentialDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authClient", url = "${com.cgi.glk.ectp.common.authURL}")
public interface AuthClient {

    @PostMapping("/authenticate")
    String authenticate(@RequestBody final CredentialDTO pCredentialDTO);

    @PostMapping("/validate")
    boolean validate(@RequestBody final String pJWT);

    @PostMapping("/logout")
    void logout(@RequestBody final String pJWT);

    @PostMapping("/adduser")
    void addUser(@RequestBody final CredentialDTO pDTO);

    @PostMapping("/validateuser")
    boolean validateUser(@RequestBody final CredentialDTO pDTO);

}
