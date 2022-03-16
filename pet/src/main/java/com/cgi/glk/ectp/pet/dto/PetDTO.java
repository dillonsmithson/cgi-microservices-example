package com.cgi.glk.ectp.pet.dto;

import com.cgi.glk.ectp.pet.model.PetModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {

    private int id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int ownerId;

    private String type;
    private String breed;
    private String gender;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date dob;

    private String color;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double weight;

    public static PetDTO of(final PetModel pPetModel) {
        return new PetDTO(pPetModel.getId(), pPetModel.getName(), pPetModel.getOwnerId(), pPetModel.getType(), pPetModel.getBreed(), pPetModel.getGender(),
                pPetModel.getDob(), pPetModel.getColor(), pPetModel.getWeight());
    }

    public PetModel toModel(final int pId) {
        val model = new PetModel();
        model.setId(pId);
        model.setName(getName());
        model.setOwnerId(getOwnerId());
        model.setType(getType());
        model.setBreed(getBreed());
        model.setGender(getGender());
        model.setColor(getColor());

        return model;
    }

    public PetModel toModel(final PetModel pPetModel) {
        val model = new PetModel();
        model.setId(pPetModel.getId());
        model.setName(getName());
        model.setOwnerId(getOwnerId());
        model.setType(getType());
        model.setBreed(getBreed());
        model.setGender(getBreed());
        model.setGender(getGender());
        model.setColor(getColor());
        model.setWeight(getWeight());

        return model;
    }
}
