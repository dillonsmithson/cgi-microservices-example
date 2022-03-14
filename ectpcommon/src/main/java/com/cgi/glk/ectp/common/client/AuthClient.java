package com.cgi.glk.ectp.common.client;

import com.cgi.glk.ectp.common.dto.CredentialDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authClient", url = "${com.cgi.glk.ectp.common.authURL}")
public interface AuthClient {

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody CredentialDTO pCredentialDTO);
    
    @PostMapping("/validate")
    public boolean validate(@RequestBody final String pJWT);

    @PostMapping("/logout")
    public void logout(@RequestBody final String pJWT);
}