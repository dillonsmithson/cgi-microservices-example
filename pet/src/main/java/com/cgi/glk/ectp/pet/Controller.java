package com.cgi.glk.ectp.pet;

import com.cgi.glk.ectp.common.service.HttpService;
import com.cgi.glk.ectp.pet.dao.PetRepository;
import com.cgi.glk.ectp.pet.dto.PetDTO;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("pet/v1")
@Slf4j
public class Controller {

    @Autowired private PetRepository petRepository;
    @Autowired private HttpService httpService;

    @PostMapping
    public PetDTO create(
            @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken,
            @RequestBody final PetDTO pPetDto
    ) {
        httpService.verifyToken(pToken);

        val model = pPetDto.toModel(Integer.MIN_VALUE);
        log.info("Created model in create - Controller for Pet {}", model.toString());
        val response = petRepository.save(model);
        log.info("new row written: {}", response);

        return PetDTO.of(response);
    }

    @GetMapping("/{petId}")
    public PetDTO read(
            @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken,
            @PathVariable("petId") final int pPetId
    ) {
        httpService.verifyToken(pToken);

        return petRepository.findById(pPetId)
                .map(p -> PetDTO.of(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public Collection<PetDTO> getAll(@RequestHeader(value = "Authorization", required = false) final Optional<String> pToken) {
        httpService.verifyToken(pToken);

        return StreamSupport.stream(petRepository.findAll().spliterator(), false)
                .map(p -> PetDTO.of(p))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{petId}")
    public void delete(
            @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken,
            @PathVariable("petId") final int pPetId
    ) {
        httpService.verifyToken(pToken);

        if(!petRepository.existsById(pPetId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        petRepository.deleteById(pPetId);
    }

    @PatchMapping("/{id}")
    public PetDTO update(
            @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken,
            @PathVariable("id") final int pId,
            @RequestBody final PetDTO pPetDTO
    ) {
        httpService.verifyToken(pToken);

        val cache = petRepository.findById(pId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //Not checking whether there are updates here.

        val model = pPetDTO.toModel(cache);
        val response = petRepository.save(model);

        return PetDTO.of(response);
    }
}
