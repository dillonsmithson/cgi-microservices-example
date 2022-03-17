package com.cgi.glk.ectp.pet;

import com.cgi.glk.ectp.common.service.HttpService;
import com.cgi.glk.ectp.pet.dao.PetRepository;
import com.cgi.glk.ectp.pet.dto.PetDTO;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
