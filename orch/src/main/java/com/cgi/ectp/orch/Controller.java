package com.cgi.ectp.orch;

import com.cgi.ectp.orch.service.OwnerService;
import com.cgi.glk.ectp.common.client.OwnerClient;
import com.cgi.glk.ectp.common.client.PetClient;
import com.cgi.glk.ectp.common.dto.CredentialDTO;
import com.cgi.glk.ectp.common.dto.OwnerAndPetDTO;
import com.cgi.glk.ectp.common.dto.OwnerDTO;
import com.cgi.glk.ectp.common.dto.PetDTO;
import com.cgi.glk.ectp.common.service.AuthService;
import com.cgi.glk.ectp.common.service.HttpService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orchestrate/v1")
@Slf4j
public class Controller {

    @Autowired private OwnerClient ownerClient;
    @Autowired private PetClient petClient;
    @Autowired private HttpService httpService;
    @Autowired private AuthService authService;
    @Autowired private OwnerService ownerService;

    @PostMapping("/auth/authenticate")
    public String authenticate(@RequestBody final CredentialDTO pCredentialDTO) {
        log.info("in Orch Controller - Authenticate");
        return authService.authenticate(pCredentialDTO);
    }

    @PostMapping("/auth/logout")
    public void logout(@RequestBody final String pJWT) {
        log.info("in Orch Controller - logout");
        authService.logout(pJWT);
    }

    @GetMapping("/owner/{ownerId}")
    public OwnerDTO read(
            @RequestHeader("Authorization") final String pToken,
            @PathVariable("ownerId") final int pOwnerId
    ) {
        log.info("in Orch Controller - read");
        httpService.validateToken(pToken);

        return httpService.propagateFeignException(() -> ownerClient.getOwner(pToken, pOwnerId)); //HttpUtils.propagateFeignException //HttpUtils imported statically
    }

    @GetMapping("/owner/")
    public List<OwnerDTO> getAll(@RequestHeader("Authorization") final String pToken) {
        log.info("in Orch Controller - getAll");
        httpService.validateToken(pToken);

        return httpService.propagateFeignException(() -> ownerClient.getAll(pToken)); //HttpUtils.propagateFeignException //HttpUtils imported statically
    }

    @GetMapping("/pet/{petId}")
    public PetDTO readPet(
            @RequestHeader("Authorization") final String pToken,
            @PathVariable("petId") final int pPetId
    ) {
        httpService.validateToken(pToken);

        return httpService.propagateFeignException(() -> petClient.read(pToken, pPetId));
    }

    @GetMapping("/pet/")
    public Collection<PetDTO> getAllPet(@RequestHeader("Authorization") final String pToken) {
        httpService.validateToken(pToken);

        return httpService.propagateFeignException(() -> petClient.getAll(pToken));
    }

    @GetMapping("/ownerAndPets/{ownerId}")
    public OwnerAndPetDTO getOwnerAndPet(@RequestHeader("Authorization") final String pToken,
                                         @PathVariable("ownerId") final int pOwnerId
    ) {
        httpService.validateToken(pToken);

        return httpService.propagateFeignException(() -> ownerService.getOwnerAndPets(pToken, pOwnerId));
    }


    @GetMapping("/ownersAndPets/")
    public List<OwnerAndPetDTO> getAllOwnersAndPets(@RequestHeader("Authorization") final String pToken) {

        httpService.validateToken(pToken);

        return httpService.propagateFeignException(() -> ownerService.getOwnersAndPets(pToken));
    }



    //graveyard from my modifications from earlier
    @PostMapping("/adduser")
    public void addUser(@RequestBody CredentialDTO pDTO) {
        authService.addUser(pDTO);
    }

    @GetMapping("/validateuser")
    public boolean validateUser(@RequestBody CredentialDTO pDTO) {
        return authService.validateUser(pDTO);
    }

}
