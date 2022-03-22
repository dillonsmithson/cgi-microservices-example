package com.cgi.ectp.orch.service;

import com.cgi.glk.ectp.common.client.OwnerClient;
import com.cgi.glk.ectp.common.client.PetClient;
import com.cgi.glk.ectp.common.dto.OwnerAndPetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    @Autowired private OwnerClient ownerClient;
    @Autowired private PetClient petClient;

    public OwnerAndPetDTO getOwnerAndPets(final String pToken, final int pOwnerId) {
        return OwnerAndPetDTO.of(
                ownerClient.getOwner(pToken, pOwnerId),
                petClient.byOwner(pToken, pOwnerId)
        );

    }

    public List<OwnerAndPetDTO> getOwnersAndPets(final String pToken) {
        return ownerClient.getAll(pToken).stream()
                .map(oDTO -> OwnerAndPetDTO.of(oDTO, petClient.byOwner(pToken, oDTO.getId())))
                .collect(Collectors.toList());
    }
}
