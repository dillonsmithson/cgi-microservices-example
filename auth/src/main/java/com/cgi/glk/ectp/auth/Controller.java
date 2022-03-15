package com.cgi.glk.ectp.auth;

import com.cgi.glk.ectp.common.dto.CredentialDTO;
import com.cgi.glk.ectp.auth.service.JWTService;
import com.cgi.glk.ectp.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth/v1")
@Slf4j
public class Controller {

    @Autowired private JWTService jwtService;
    @Autowired private UserService userService;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody final CredentialDTO pCredentialDTO) {
        log.info("in Auth Controller - Authenticate");
        return jwtService.forge(pCredentialDTO).orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN)); //forbidden if empty optional
    }

    @PostMapping("/validate")
    public boolean validate(@RequestBody final String pJWT) {
        log.info("in Auth Controller - validate");
        return jwtService.validate(pJWT);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody final String pJWT) {
        log.info("in Auth Controller - Logout");
        jwtService.invalidate(pJWT);
    }

    @PostMapping("/adduser")
    public void addUser(
            @RequestBody final CredentialDTO pDTO
    ) {
        log.info("This is unused, shouldn't print");
        userService.addUser(pDTO);
    }

    @PostMapping("/validateuser")
    public boolean validateUser(
            @RequestBody final CredentialDTO pDTO
    ) {
        log.info("This is unused, shouldn't print");
        return userService.validateUser(pDTO);
    }

}
