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
        log.info("Authenticating user credentials");
        return authClient.authenticate(pCredentialDTO);
    }

    public void logout(final String pJWT) {
        log.info("User logged out.");
        authClient.logout(pJWT);
    }

    public boolean validateToken(final String pJWT) {
        if (!pJWT.startsWith(BEARER)) {
            log.warn("JWT doesn't start with correct identifier.");
            return false;
        }

        log.info("Fetching JWT");
        val token = fetchToken(pJWT);

        log.info("Preparing to validate {}", token);
        return validate(token);
    }

    public boolean verifyToken(final String pToken) {
        log.info("Verifying token {}", pToken);
        return authClient.validate(fetchToken(pToken));
    }

    public boolean validate(final String pJWT) {
        try {
            jwtVerifier.verify(pJWT);
        } catch (final JWTVerificationException e) {
            System.out.println(pJWT);
            log.warn("Invalid JWT", e);
            return false;
        }

        return true;
    }

    public String fetchToken(final String pToken) {
        return pToken.substring(BEARER.length());
    }
}
