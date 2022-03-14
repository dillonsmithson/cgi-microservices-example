package com.cgi.glk.ectp.common.service;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class HttpService {

    @Autowired private AuthService authService;

    public void validateToken(final String pToken) {
        if (!authService.validateToken(pToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public void verifyToken(final Optional<String> pToken) {
        if (    !pToken
                .map(t -> authService.verifyToken(t))
                .orElse(false)  ){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public <T> T propagateFeignException(final Supplier<T> pSupplier) {
        try {
            return pSupplier.get();
        } catch (final FeignException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.status()));
        }
    }
}
