package com.cgi.ectp.crud.owner;

import com.cgi.ectp.crud.owner.dao.OwnerRepository;
import com.cgi.ectp.crud.owner.dto.OwnerDTO;
import com.cgi.glk.ectp.common.service.HttpService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/owner/v1")
@Slf4j
public class Controller {

    @Autowired private OwnerRepository ownerRepository;
    @Autowired private HttpService httpService;

    @PostMapping
    public OwnerDTO create(
            @RequestHeader(name = "Authorization", required = false) final Optional<String> pToken,
            @RequestBody final OwnerDTO pDTO
    ) {
        log.info("Verifying token");
        httpService.verifyToken(pToken);

        log.info("Creating new owner model.");
        val model = pDTO.toModel(Integer.MIN_VALUE);
        model.setCreated(new Date());
        model.setCreatedBy("SYS");

        val response = ownerRepository.save(model);
        log.info("new row written: {}", response);
        return OwnerDTO.of(response);
    }

    @GetMapping("/{id}")
    public OwnerDTO read(
        @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken,
        @PathVariable("id") final int pId
    ) {
        log.info("Verifying token");
        httpService.verifyToken(pToken);

        log.info("Collecting owners from database");
        return ownerRepository.findById(pId)
                .map(OwnerDTO::of)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public OwnerDTO replace(
            @RequestHeader(name = "Authorization", required = false) final Optional<String> pToken,
            @PathVariable("id") final int pId, @RequestBody final OwnerDTO pDTO
    ){
        httpService.verifyToken(pToken);

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
            @RequestHeader(name = "Authorization", required = false) final Optional<String> pToken,
            @PathVariable("id") final int pId, @RequestBody final OwnerDTO pDTO
    ){
        httpService.verifyToken(pToken);

        val cache = ownerRepository.findById(pId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!pDTO.hasUpdates()) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
        }

        val model = pDTO.toModel(cache, "SYS");

        val response = ownerRepository.save(model);
        log.info("Updated row: {}", response);
        return OwnerDTO.of(response);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @RequestHeader(name = "Authorization", required = false) final Optional<String> pToken,
            @PathVariable("id") final int pId
    ) {
        httpService.verifyToken(pToken);

        if (!ownerRepository.existsById(pId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        ownerRepository.deleteById(pId);
    }

    @GetMapping("/")
    public Collection<OwnerDTO> getAll(
            @RequestHeader(name = "Authorization", required = false) final Optional<String> pToken
    ) {
        httpService.verifyToken(pToken);

        return StreamSupport
                .stream(ownerRepository.findAll().spliterator(), false)
                .map(OwnerDTO::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/byName")
    public Collection<OwnerDTO> byName(
            @RequestHeader(name = "Authorization", required = false) final Optional<String> pToken,
            @RequestParam("name") final String pName
    ) {
        httpService.verifyToken(pToken);

        return ownerRepository.byName(pName)
                .stream()
                .map(OwnerDTO::of)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.HEAD, path = "/{id}")
    public void head(
            @RequestHeader(name = "Authorization", required = false) final Optional<String> pToken,
            @PathVariable("id") final int pId
    ) {
        httpService.verifyToken(pToken);

        ownerRepository.findById(pId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
