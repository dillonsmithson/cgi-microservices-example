package com.cgi.glk.ectp.common.client;

import com.cgi.glk.ectp.common.dto.PetDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface PetClient {

    @GetMapping(value ="/{petId}}", produces = "application/json")
    PetDTO getPet(@PathVariable("petId") final int pPetId);

}
