package com.cgi.glk.ectp.auth.service;

import com.cgi.glk.ectp.common.dto.CredentialDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    private static Map<String, String> userPass = new HashMap<>();

    public void addUser(final CredentialDTO pDTO) {
        log.info("added user in UserDTO");
        userPass.put(pDTO.getUsername(), pDTO.getPassword());
    }

    public boolean validateUser(final CredentialDTO pDTO) {
        //TODO refactor
        //log.info("attempting to validate user.....");
        //log.info("Username: {} and Key {}", pDTO.getUsername(), userPass.containsKey(pDTO.getUsername()));
        //log.info("Password: {} and get {}", pDTO.getPassword(), userPass.get(pDTO.getUsername()).equals(pDTO.getPassword()));
        if (userPass.containsKey(pDTO.getUsername()) && userPass.get(pDTO.getUsername()).equals(pDTO.getPassword())) {
            log.info("User Validated");
            return true;
        } else {
            //log.info("User NOT validated");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            //return false; //?
        }
    }
}
