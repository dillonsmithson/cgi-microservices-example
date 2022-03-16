package com.cgi.glk.ectp.pet;

import com.cgi.glk.ectp.pet.dao.PetRepository;
import com.cgi.glk.ectp.pet.dto.PetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;


public class Controller {

    @Autowired private PetRepository petRepository;

    public void create(@RequestBody final PetDTO pPetDto) {
         //need to account for the integer

        //Should also return PetDTO
    }

    @GetMapping("/{petId}")
    public PetDTO read(@PathVariable("petId") final int pPetId) {
        return petRepository.findById(pPetId)
                .map(p -> PetDTO.of(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
