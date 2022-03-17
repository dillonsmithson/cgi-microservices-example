package com.cgi.glk.ectp.common.dto;

import com.cgi.glk.ectp.common.client.OwnerClient;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OwnerAndPetDTO {

    private OwnerDTO ownerDTO;
    private List<PetDTO> listOfPets;

    public static OwnerAndPetDTO of(final OwnerDTO pOwnerDTO, final List<PetDTO> pListPet) {
        return new OwnerAndPetDTO(pOwnerDTO, pListPet);
    }
}
