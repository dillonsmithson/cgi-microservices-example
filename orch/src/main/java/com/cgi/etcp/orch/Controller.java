package com.cgi.etcp.orch;

import com.cgi.etcp.orch.dto.OwnerDTO;
import com.cgi.etcp.orch.client.OwnerClient;
import com.cgi.etcp.orch.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orchestrate/v1")
public class Controller {

    @Autowired private OwnerClient ownerClient;
    @Autowired private Properties properties;
    @Autowired private AuthService authService;

    @GetMapping("/owner/{ownerId}")
    public OwnerDTO getOwner(
            @RequestHeader("Authorization") final String pToken,
            @PathVariable("ownerId") final int pOwnerId
    ) {
        if (!authService.validateToken(pToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return ownerClient.read(properties.getSecret(), pOwnerId);
    }

    @GetMapping("/owner/")
    public List<OwnerDTO> getAll(@RequestHeader("Authorization") final String pToken) {
        if (!authService.validateToken(pToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return ownerClient.getAll();
    }
}
