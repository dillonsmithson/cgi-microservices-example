package com.cgi.glk.ectp.common.client;

import com.cgi.glk.ectp.common.dto.OwnerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "orchestrate", url = "${com.cgi.glk.ectp.common.ownerURL}")
public interface OwnerClient {

    @GetMapping(value = "/{ownerId}", produces = "application/json")
    OwnerDTO getOwner(@RequestHeader("Authorization") String secret, @PathVariable("ownerId") final int pOwnerId);

    @GetMapping(value = "/", produces = "application/json")
    List<OwnerDTO> getAll(@RequestHeader(name = "Authorization", defaultValue = "") final String pToken);

}
