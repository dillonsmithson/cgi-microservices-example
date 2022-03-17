package com.cgi.glk.ectp.pet.dto;

import com.cgi.glk.ectp.common.dto.OwnerDTO;
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
    private int ownerId;
    private String type;
    private String breed;
    private String gender;
    private String color;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date dob;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private float currentWeight;

    public boolean hasUpdates() {
        return (
                (getName()      != null)    || // or
                (getType()      != null)    || // or
                (getBreed()     != null)    || // or
                (getGender()    != null)    || // or
                (getColor()     != null)
        );
    }

    public static PetDTO of(final PetModel pModel) {
        return new PetDTO(  pModel.getId(),
                            pModel.getName(),
                            pModel.getOwnerId(),
                            pModel.getType(),
                            pModel.getBreed(),
                            pModel.getGender(),
                            pModel.getColor(),
                            pModel.getDob(),
                            pModel.getCurrentWeight()   );
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

    public PetModel toModel(final PetModel pModel) {
        val model = new PetModel();
        model.setId(pModel.getId());
        model.setOwnerId(pModel.getOwnerId());

        model.setName(      (getName() == null)     ? pModel.getName() : getName());
        model.setType(      (getType() == null)     ? pModel.getType() : getType());
        model.setBreed(     (getBreed() == null)    ? pModel.getBreed() : getBreed());
        model.setGender(    (getGender() == null)   ? pModel.getGender() : getGender());
        model.setColor(     (getColor() == null)    ? pModel.getColor() : getColor());

        return model;
    }
}
