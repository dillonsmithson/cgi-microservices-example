package com.cgi.ectp.auth;

import com.cgi.ectp.auth.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
public class Controller {

    @Autowired private JWTService jwtService;

    @PostMapping("/authenticate")
    public String authenticate() {
        return jwtService.forge();
    }

    @PostMapping("/validate")
    public boolean validate(@RequestBody final String pJWT) {
        return jwtService.validate(pJWT);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody final String pJWT) {

    }
}
