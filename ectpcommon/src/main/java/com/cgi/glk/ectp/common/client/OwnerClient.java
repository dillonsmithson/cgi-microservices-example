package com.cgi.glk.ectp.common.client;

import com.cgi.glk.ectp.common.dto.OwnerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Collection;

@FeignClient(name = "orchestrate-api", url = "${com.cgi.glk.ectp.common.ownerURL}")
public interface OwnerClient {

    @GetMapping("/{id}")
    OwnerDTO read(
            @RequestHeader(name = "Authorization", defaultValue = "") final String pToken,
            @PathVariable("id") final int pId
    );

    @GetMapping("/")
    Collection<OwnerDTO> getAll(@RequestHeader(name = "Authorization", defaultValue = "") final String pToken);
}