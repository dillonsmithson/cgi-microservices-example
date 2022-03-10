package com.cgi.ectp.crud.owner.service;

import feign.FeignException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class HttpService {

    @Autowired
    AuthService authService;

    public void validateToken(final Optional<String> pToken) {
        val token = pToken.orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));
        if (!authService.validateToken(token)) {
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
