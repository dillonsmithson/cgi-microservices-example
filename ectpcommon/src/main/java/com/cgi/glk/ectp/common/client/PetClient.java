package com.cgi.glk.ectp.common.client;

import com.cgi.glk.ectp.common.dto.PetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@FeignClient(name = "pet", url = "${com.cgi.glk.ectp.common.petURL}")
public interface PetClient {

    @GetMapping(value ="/{petId}}", produces = "application/json")
    PetDTO read(@RequestHeader(value = "Authorization", required = false) final String pToken, @PathVariable("petId") final int pPetId);

    @GetMapping(value ="/", produces = "application/json")
    Collection<PetDTO> getAll(@RequestHeader(value = "Authorization", required = false) final String pToken);

}
