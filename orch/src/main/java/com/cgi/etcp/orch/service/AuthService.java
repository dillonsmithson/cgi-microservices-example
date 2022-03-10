package com.cgi.etcp.orch.service;

import com.cgi.etcp.orch.client.AuthClient;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired private AuthClient authClient;

    private final String BEARER = "Bearer ";

    public boolean validateToken(final String pJWT) {
        if (!pJWT.startsWith(BEARER)) {
            return false;
        }

        val token = pJWT.substring(BEARER.length());


        return authClient.validate(token);
    }
}
