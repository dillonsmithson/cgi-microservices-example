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
            @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken,
            @RequestBody final OwnerDTO pDTO
    ) {
        log.info("in Owner Controller - create");
        httpService.verifyToken(pToken);

        val model = pDTO.toModel(Integer.MIN_VALUE); //save as something that isn't already in the database
        model.setCreated(new Date());
        model.setCreatedBy("SYS");

        val response = ownerRepository.save(model);
        log.info("new row written: {}", response);
        return OwnerDTO.of(response); //factory method to create OwnerDTO from a OwnerModel
    }

    @GetMapping("/{id}")
    public OwnerDTO read(
            @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken,
            @PathVariable("id") final int pId
    ) {
        log.info("in Owner Controller - read");
        httpService.verifyToken(pToken);

        return ownerRepository.findById(pId)
                .map(m -> OwnerDTO.of(m))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public OwnerDTO replace(
        @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken,
        @PathVariable("id") final int pId,
        @RequestBody final OwnerDTO pDTO
    ) {
        log.info("in Owner Controller - replace");
        httpService.verifyToken(pToken);

        val cache = ownerRepository.findById(pId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        val model = pDTO.toModel(pId);
        model.setCreated(cache.getCreated());
        model.setCreatedBy(cache.getCreatedBy());
        model.setUpdated(new Date());
        model.setUpdatedBy("SYS");

        val response = ownerRepository.save(model);
        log.info("updated row : {}", response);
        return OwnerDTO.of(response);

    }

    @PatchMapping("/{id}")
    public OwnerDTO update(
            @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken,
            @PathVariable("id") final int pId,
            @RequestBody final OwnerDTO pDTO
    ) {
        log.info("in Owner Controller - update");

        httpService.verifyToken(pToken);

        val cache = ownerRepository.findById(pId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!pDTO.hasUpdates()) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
        }
        val model = pDTO.toModel(cache, "SYS");

        val response = ownerRepository.save(model);
        log.info("updated row : {}", response);
        return OwnerDTO.of(response);

    }

    @DeleteMapping("/{id}")
    public void delete(
            @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken,
            @PathVariable("id") final int pId
    ) {
        log.info("in Owner Controller - delete");

        httpService.verifyToken(pToken);

        ownerRepository.deleteById(pId);
    }
    @GetMapping("/")
    public Collection<OwnerDTO> getAll(
            @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken
    ) {
        log.info("in Owner Controller - getAll");
        httpService.verifyToken(pToken);

        return StreamSupport.stream(ownerRepository.findAllOrdered().spliterator(), false)
                .map(m -> OwnerDTO.of(m))
                .collect(Collectors.toList());
    }

    @GetMapping("/byName")
    public Collection<OwnerDTO> byName(
            @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken,
            @RequestParam("name") final String pName
    ) {
        log.info("in Owner Controller - byName");
        httpService.verifyToken(pToken);

        return ownerRepository.byName(pName).stream()//owner repository to list to stream
                .map(m -> OwnerDTO.of(m))
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.HEAD})
    public void head(
            @RequestHeader(value = "Authorization", required = false) final Optional<String> pToken,
            @PathVariable("id") final int pId
    ) {
        log.info("in Owner Controller - head");
        httpService.verifyToken(pToken);

        if (!ownerRepository.existsById(pId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); //throw 404
        }
    }
    /*
    //demo of supplier/consumer lambda notation
    val colors = Arrays.asList("red", "black", "blue", "orange");
    colors.stream()                                 // red, blue, black orange
            .filter(c -> c.startsWith("b"))         // blue black
            .map(s -> s.length())                   // 4,5
            .forEach(n -> System.out.println(n));

    val bColorLengths = colors.stream()                                   // red, blue, black, orange
            .filter(c -> c.startsWith("b"))                               //blue, black
            .collect(Collectors.toMap(s -> s, s -> s.length()));          //[{blue: 4}, {black: 5}]

    System.out.println(Optional.ofNullable(bColorLengths.get(color))
            .orElse(() -> strLength(color)); //will supply if asked. In this example, never gets to this point.
            // doing orElse(strLength(color)) will ALWAYS call this function.
    private int strLength(final String pString) {
        return pString.length();
    }
    */

}
