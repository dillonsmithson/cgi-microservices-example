package com.cgi.etcp.crud.owner;

import com.cgi.etcp.crud.owner.dao.OwnerRepository;
import com.cgi.etcp.crud.owner.dto.OwnerDTO;
import com.cgi.etcp.crud.owner.service.AuthenService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/owner/v1")
@Slf4j
public class Controller {

    @Autowired private OwnerRepository ownerRepository;
    @Autowired private AuthenService authenService;

    @PostMapping
    public OwnerDTO create(@RequestBody final OwnerDTO pDTO) {
        val model = pDTO.toModel(Integer.MIN_VALUE);
        model.setCreated(new Date());
        model.setCreatedBy("SYS");

        val response = ownerRepository.save(model);
        log.info("new row written: {}", response);
        return OwnerDTO.of(response);
    }

    @GetMapping("/{id}")
    public OwnerDTO read(
        @RequestHeader(name="Authorization", defaultValue = "") final String pToken,
        @PathVariable("id") final int pId
    ) {
        if (!authenService.checkAuthen(pToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return ownerRepository.findById(pId)
                .map(m -> OwnerDTO.of(m))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public OwnerDTO replace(
        @PathVariable("id") final int pId,
        @RequestBody() final OwnerDTO pDTO
    ) {
        val cache = ownerRepository.findById(pId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        val model = pDTO.toModel(pId);
        model.setCreated(cache.getCreated());
        model.setCreatedBy(cache.getCreatedBy());
        model.setUpdated(new Date());
        model.setUpdatedBy("SYS");

        val response = ownerRepository.save(model);
        log.info("Updated row: {}", response);
        return OwnerDTO.of(response);
    }

    @PatchMapping("/{id}")
    public OwnerDTO update(
            @PathVariable("id") final int pId,
            @RequestBody() final OwnerDTO pDTO
    ) {

        val cache = ownerRepository.findById(pId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!pDTO.hasUpdates()) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
        }

        val model = pDTO.toModel(cache, "SYS");
        model.setCreated(cache.getCreated());
        model.setCreatedBy(cache.getCreatedBy());
        model.setUpdated(new Date());
        model.setUpdatedBy("SYS");

        val response = ownerRepository.save(model);
        log.info("Updated row: {}", response);
        return OwnerDTO.of(response);
    }

    @PostMapping("/setLang")
    public OwnerDTO setLanguage(
            @RequestParam("id") final int pId,
            @RequestBody() final OwnerDTO pDTO
    ) {
        log.info("Body?");
        log.info(pDTO.toString());
        val cache = ownerRepository.findById(pId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        val model = pDTO.toModel(cache, "SYS");
        model.setCreated(cache.getCreated());
        model.setCreatedBy(cache.getCreatedBy());
        model.setUpdated(new Date());
        model.setUpdatedBy("SYS");
        model.setLanguage(pDTO.getLanguage());

        val response = ownerRepository.save(model);
        log.info("Updated row: {}", response);
        return OwnerDTO.of(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final int pId) {
        if (!ownerRepository.existsById(pId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        ownerRepository.deleteById(pId);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.HEAD)
    public void head(@PathVariable("id") final int pId) {
        if (!ownerRepository.existsById(pId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public Collection<OwnerDTO> getAll() {
        return StreamSupport.stream(ownerRepository.findAll().spliterator(), false)
                .map(m -> OwnerDTO.of(m))
                .collect(Collectors.toList());
    }

    @GetMapping("/byName")
    public Collection<OwnerDTO> byName(@RequestParam("name") final String pName) {
        return ownerRepository.byName(pName).stream()
                .map(m -> OwnerDTO.of(m))
                .collect(Collectors.toList());
    }
}

