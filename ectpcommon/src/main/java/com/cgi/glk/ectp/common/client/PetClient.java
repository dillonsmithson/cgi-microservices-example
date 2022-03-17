package com.cgi.glk.ectp.common.client;

import com.cgi.glk.ectp.common.dto.PetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Collection;

@FeignClient(name = "pet-api", url = "${com.cgi.glk.ectp.common.petURL}")
public interface PetClient {

    @GetMapping("/{id}")
    PetDTO read(
            @RequestHeader(name = "Authorization", defaultValue = "") final String pToken,
            @PathVariable("id") final int pId
    );

    @GetMapping("/")
    Collection<PetDTO> getAll(@RequestHeader(name = "Authorization", defaultValue = "") final String pToken);
}
