package com.cgi.glk.ectp.auth;

import com.cgi.glk.ectp.auth.service.JWTService;
import com.cgi.glk.ectp.common.Properties;
import com.cgi.glk.ectp.common.dto.CredentialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth/v1")
public class Controller {

    @Autowired private Properties properties;
    @Autowired private JWTService jwtService;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody final CredentialDTO pCredentialDTO) {
        return  jwtService.forge(pCredentialDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));
    }

    @PostMapping("/validate")
    public boolean validate(@RequestBody final String pJWT) {
        return jwtService.validate(pJWT);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody final String pJWT) {
        jwtService.delete(pJWT);
    }
}
