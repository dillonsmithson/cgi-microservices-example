package com.cgi.glk.ectp.pet.dto;

import com.cgi.glk.ectp.pet.model.PetModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import java.util.Date;

import static com.cgi.glk.ectp.pet.Utils.coalesce;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {

    private int id;
    private String name;
    private Integer ownerId;
    private String type;
    private String breed;
    private String gender;
    private String color;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date dob;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double currentWeight;

    public boolean hasUpdates() {
        return (
                coalesce(
                        getName(),
                        getOwnerId(),
                        getType(),
                        getBreed(),
                        getGender(),
                        getColor(),
                        getDob(),
                        getCurrentWeight()
                ) != null
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

        model.setName(          coalesce(   getName(),             pModel.getName()             ));
        model.setType(          coalesce(   getType(),             pModel.getType()             ));
        model.setBreed(         coalesce(   getBreed(),            pModel.getBreed()            ));
        model.setGender(        coalesce(   getGender(),           pModel.getGender()           ));
        model.setColor(         coalesce(   getColor(),            pModel.getColor()            ));
        model.setCurrentWeight( coalesce(   getCurrentWeight(),    pModel.getCurrentWeight()    ));

        return model;
    }
}
