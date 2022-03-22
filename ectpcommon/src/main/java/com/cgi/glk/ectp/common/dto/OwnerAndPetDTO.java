package com.cgi.glk.ectp.common.dto;

import com.cgi.glk.ectp.common.client.OwnerClient;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of") //create factory method "of" and private all args constructor
public class OwnerAndPetDTO {

    private OwnerDTO ownerDTO;
    private List<PetDTO> listOfPets;

}
