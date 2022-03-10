package com.cgi.ectp.orch.client;

import com.cgi.ectp.orch.dto.OwnerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@FeignClient(name = "orchestrate-api", url = "${com.cgi.ectp.orch.ownerURL}")
public interface OwnerClient {

    @GetMapping("/{id}")
    OwnerDTO read(
            @RequestHeader(name = "Authorization", defaultValue = "") final String pToken,
            @PathVariable("id") final int pId
    );

    @GetMapping("/")
    Collection<OwnerDTO> getAll(@RequestHeader(name = "Authorization", defaultValue = "") final String pToken);
}