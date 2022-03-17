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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/pet/v1")
@Slf4j
public class Controller {

    @Autowired private PetRepository petRepository;
    @Autowired private HttpService httpService;

    @PostMapping
    public PetDTO create(@RequestBody final PetDTO pDTO) {
        var model = pDTO.toModel(Integer.MIN_VALUE);

        val response = petRepository.save(model);
        log.info("New row written to pet database: {}", response);
        return PetDTO.of(model);
    }

    @GetMapping("/{id}")
    public PetDTO read(@PathVariable("id") final int pId) {

        log.info("Collecting pet from database.");
        return petRepository.findById(pId)
                .map(PetDTO::of)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public PetDTO replace(@PathVariable("id") final int pId, @RequestBody final PetDTO pDTO) {
        val cache = petRepository.findById(pId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        val model = pDTO.toModel(pId);
        val response = petRepository.save(model);
        log.info("Updated row: {}", response);
        return PetDTO.of(response);
    }

    @PatchMapping("/{id}")
    public PetDTO update(@PathVariable("id") final int pId, @RequestBody final PetDTO pDTO) {
        val cache = petRepository.findById(pId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(!pDTO.hasUpdates()) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
        }

        val model = pDTO.toModel(cache);
        val response = petRepository.save(model);
        log.info("Updated row: {}", response);
        return PetDTO.of(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final int pId) {
        if (!petRepository.existsById(pId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        petRepository.deleteById(pId);
    }

    @GetMapping("/")
    public Collection<PetDTO> getAll() {
        return StreamSupport
                .stream(petRepository.findAll().spliterator(), false)
                .map(PetDTO::of)
                .collect(Collectors.toList());
    }
}
