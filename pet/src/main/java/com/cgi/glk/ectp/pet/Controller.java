package com.cgi.glk.ectp.pet;

import com.cgi.glk.ectp.pet.dao.PetRepository;
import com.cgi.glk.ectp.pet.dto.PetDTO;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("pet/v1")
@Slf4j
public class Controller {

    @Autowired private PetRepository petRepository;

    @PostMapping
    public PetDTO create(@RequestBody final PetDTO pPetDto) {

        val model = pPetDto.toModel(Integer.MIN_VALUE);
        log.info("Created model in create - Controller for Pet {}", model.toString());
        val response = petRepository.save(model);
        log.info("new row written: {}", response);

        return PetDTO.of(response);
    }

    @GetMapping("/{petId}")
    public PetDTO read(@PathVariable("petId") final int pPetId) {
        return petRepository.findById(pPetId)
                .map(p -> PetDTO.of(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
