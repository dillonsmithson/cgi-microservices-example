package com.cgi.glk.ectp.pet.dto;

import com.cgi.glk.ectp.pet.Utils;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date dob;

    private String color;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double weight;

    public static PetDTO of(final PetModel pPetModel) {
        return new PetDTO(pPetModel.getId(), pPetModel.getName(), pPetModel.getOwnerId(), pPetModel.getType(),
                pPetModel.getBreed(), pPetModel.getGender(), pPetModel.getDob(), pPetModel.getColor(),
                pPetModel.getWeight());
    }

    public boolean hasUpdates() {
        return (coalesce(getName(), getOwnerId(), getType(), getBreed(), getGender(), getDob(), getColor(), getWeight()) != null);
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
        model.setName(      coalesce(   getName(),      pPetModel.getName())    );
        model.setOwnerId(   coalesce(   getOwnerId(),   pPetModel.getOwnerId()) );
        model.setType(      coalesce(   getType(),      pPetModel.getType())    );
        model.setBreed(     coalesce(   getBreed(),     pPetModel.getBreed())   );
        model.setGender(    coalesce(   getGender(),    pPetModel.getGender())  );
        model.setDob(       coalesce(   getDob(),       pPetModel.getDob())     );
        model.setColor(     coalesce(   getColor(),     pPetModel.getColor())   );
        model.setWeight(    coalesce(   getWeight(),    pPetModel.getWeight())  );

        return model;
    }
}
