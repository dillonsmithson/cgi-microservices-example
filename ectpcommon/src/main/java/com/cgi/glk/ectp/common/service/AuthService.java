package com.cgi.glk.ectp.common.service;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cgi.glk.ectp.common.client.AuthClient;
import com.cgi.glk.ectp.common.dto.CredentialDTO;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    @Autowired private AuthClient authClient;
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

    public boolean verifyToken(final String pToken) {
        log.info("Verifying token {}", pToken);
        return authClient.validate(fetchToken(pToken));
    }

    public String fetchToken(final String pToken) {
        return pToken.substring(BEARER.length());
    }
}
