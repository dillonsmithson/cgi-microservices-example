package com.cgi.etcp.orch.client;

import com.cgi.etcp.orch.dto.OwnerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="orch", url="${com.cgi.etcp.orch.ownerURL}")
public interface OwnerClient {

    @GetMapping("/{id}")
    OwnerDTO read(
            @RequestHeader(name="Authorization", defaultValue = "") final String secret,
            @PathVariable("id") final int pId
    );

    @GetMapping("/")
    List<OwnerDTO> getAll();
}

