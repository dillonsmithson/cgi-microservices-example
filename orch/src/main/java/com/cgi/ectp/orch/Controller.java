package com.cgi.ectp.orch;

import com.cgi.glk.ectp.common.client.OwnerClient;
import com.cgi.glk.ectp.common.client.PetClient;
import com.cgi.glk.ectp.common.dto.CredentialDTO;
import com.cgi.glk.ectp.common.dto.OwnerDTO;
import com.cgi.glk.ectp.common.dto.PetDTO;
import com.cgi.glk.ectp.common.service.AuthService;
import com.cgi.glk.ectp.common.service.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/orchestrate/v1")
public class Controller{

    @Autowired private OwnerClient ownerClient;
    @Autowired private PetClient petClient;
    @Autowired private HttpService httpService;
    @Autowired private AuthService authService;

    @PostMapping("/auth/authenticate")
    public String authenticate(@RequestBody final CredentialDTO pCredentialDTO) {
        log.info("Sending Credential DTO to authService to authenticate");
        return authService.authenticate(pCredentialDTO);
    }

    @PostMapping("/auth/logout")
    public void logout(@RequestBody final String pJWT) {
        log.info("Sending JWT to log user out.");
        authService.logout(pJWT);
    }

    @GetMapping("/owner/{ownerId}")
    public OwnerDTO getOwner(
            @PathVariable("ownerId") final int pOwnerId,
            @RequestHeader("Authorization") final String pToken
    ) {
        log.info("Sending token to be validated.");
        httpService.validateToken(pToken);

        return httpService.propagateFeignException(() -> ownerClient.read(pToken, pOwnerId));
    }

    @GetMapping("/owner/")
    public Collection<OwnerDTO> getAllOwner(@RequestHeader("Authorization") final String pToken) {
        log.info("Sending token to be validated.");
        httpService.validateToken(pToken);

        return httpService.propagateFeignException(() -> ownerClient.getAll(pToken));
    }

    @GetMapping("/pet/{petId}")
    public PetDTO getPet(
            @RequestHeader("Authorization") final String pToken,
            @PathVariable("petId") final int pPetId) {
        log.info("Getting pet");
        httpService.validateToken(pToken);

        return httpService.propagateFeignException(() -> petClient.read(pToken, pPetId));
    }

    @GetMapping("/pet/")
    public Collection<PetDTO> getAllPet(@RequestHeader("Authorization") final String pToken) {
        log.info("Getting all pets");
        httpService.validateToken(pToken);

        return httpService.propagateFeignException(() -> petClient.getAll(pToken));
    }
}