package com.cgi.glk.ectp.common.service;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cgi.glk.ectp.common.client.AuthClient;
import com.cgi.glk.ectp.common.dto.CredentialDTO;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class AuthService {
    @Autowired private AuthClient authClient;
    @Autowired private JWTVerifier jwtVerifier;

    private final String BEARER = "Bearer ";

    public String authenticate(final CredentialDTO pCredentialDTO) {
        log.info("In authenticate in AuthService in Library");
        return authClient.authenticate(pCredentialDTO);
    }

    public void logout(final String pJWT) {
        log.info("AuthService in library - logout");
        authClient.logout(pJWT);
    }

    public boolean validateToken(final String pJWT) {
        log.info("AuthService in library - validateToken");
        if (!pJWT.startsWith(BEARER)) { //keep these if statements seperate. If there is no bearer, substring could throw exception for pJWT being shorter than BEARER
            return false;
        }

        val token = fetchToken(pJWT); //leave this in seperate line so extra strips can be done later

        log.info("Preparing to validate {}", token);
        return validate(token); //Just mathematically validating the token
    }

    public boolean validate(final String pJWT) {
        log.info("AuthService in library - validate");
        try {
            jwtVerifier.verify(pJWT);
        } catch (final JWTVerificationException e) {
            log.warn("Invalid JWT", e);
            return false;
        }

        return true;
    }

    public boolean verifyToken(final String pToken) {
        log.info("AuthService in library - verifyToken");
        log.info("Validating token {}", pToken);
        return authClient.validate(fetchToken(pToken)); //parse token strips BEARER
    }

    public String fetchToken(final String pToken) {
        log.info("AuthService in library - fetchToken");
        return pToken.substring(BEARER.length());
    }

    public void addUser(final CredentialDTO pDTO) {
        authClient.addUser(pDTO);
    }

    public boolean validateUser(final CredentialDTO pDTO) {
        return authClient.validateUser(pDTO);
    }

}
