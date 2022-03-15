package com.cgi.ectp.orch;

import com.cgi.glk.ectp.common.client.OwnerClient;
import com.cgi.glk.ectp.common.dto.CredentialDTO;
import com.cgi.glk.ectp.common.dto.OwnerDTO;
import com.cgi.glk.ectp.common.service.AuthService;
import com.cgi.glk.ectp.common.service.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orchestrate/v1")
@Slf4j
public class Controller {

    @Autowired private OwnerClient ownerClient;
    @Autowired private HttpService httpService;
    @Autowired private AuthService authService;

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

    @PostMapping("/adduser")
    public void addUser(@RequestBody CredentialDTO pDTO) {
        authService.addUser(pDTO);
    }

    @GetMapping("/validateuser")
    public boolean validateUser(@RequestBody CredentialDTO pDTO) {
        return authService.validateUser(pDTO);
    }

}
