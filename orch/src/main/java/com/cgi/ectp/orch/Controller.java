package com.cgi.ectp.orch;

import com.cgi.ectp.orch.dto.CredentialDTO;
import com.cgi.ectp.orch.dto.OwnerDTO;
import com.cgi.ectp.orch.client.OwnerClient;
import com.cgi.ectp.orch.service.AuthService;
import com.cgi.ectp.orch.service.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/orchestrate/v1")
public class Controller{

    @Autowired private OwnerClient ownerClient;
    @Autowired private HttpService httpService;
    @Autowired private AuthService authService;

    @PostMapping("/auth/authenticate")
    public String authenticate(@RequestBody final CredentialDTO pCredentialDTO) {
        return authService.authenticate(pCredentialDTO);
    }

    @PostMapping("/auth/logout")
    public void logout(@RequestBody final String pJWT) {
        authService.logout(pJWT);
    }

    @GetMapping("/owner/{ownerId}")
    public OwnerDTO getOwner(
            @PathVariable("ownerId") final int pOwnerId,
            @RequestHeader("Authorization") final String pToken
    ) {
        httpService.validateToken(pToken);

        return httpService.propagateFeignException(() -> ownerClient.read(pToken, pOwnerId));
    }

    @GetMapping("/")
    public Collection<OwnerDTO> getAll(@RequestHeader("Authorization") final String pToken) {
        httpService.validateToken(pToken);

        return httpService.propagateFeignException(() -> ownerClient.getAll(pToken));
    }
}