package com.cgi.ectp.orch.service;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cgi.ectp.orch.Properties;
import com.cgi.ectp.orch.client.AuthClient;
import com.cgi.ectp.orch.dto.CredentialDTO;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    @Autowired private AuthClient authClient;
    @Autowired private Properties properties;
    @Autowired private JWTVerifier jwtVerifier;

    private static final String BEARER = "Bearer ";

    public String authenticate(final CredentialDTO pCredentialDTO) {
        return authClient.authenticate(pCredentialDTO);
    }

    public void logout(final String pJWT) {
        authClient.logout(pJWT);
    }

    public boolean validateToken(final String pJWT) {
        log.info("Ready to validate");
        if (!pJWT.startsWith(BEARER)) {
            return false;
        }

        val token = fetchToken(pJWT);

        try {
            jwtVerifier.verify(token);
        } catch (final JWTVerificationException e) {
            System.out.println(token);
            log.warn("Invalid JWT", e);
            return false;
        }

        return true;
    }

    public String fetchToken(final String pToken) {
        return pToken.substring(BEARER.length());
    }
}
