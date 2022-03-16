package com.cgi.glk.ectp.common.client;

import com.cgi.glk.ectp.common.dto.PetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pet", url = "${com.cgi.glk.ectp.common.petURL}")
public interface PetClient {

    @PostMapping
    PetDTO create(@RequestBody final PetDTO pPetDTO);

    @GetMapping(value ="/{petId}}", produces = "application/json")
    PetDTO read(@PathVariable("petId") final int pPetId);

}
