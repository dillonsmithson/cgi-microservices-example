package com.cgi.ectp.crud.owner.dto;

import com.cgi.ectp.crud.owner.model.OwnerModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {

    private int id;
    private String name;
    private String language;
    private Date created;
    private String createdBy;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date updated;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String updatedBy;

    public static OwnerDTO of(final OwnerModel pModel) {
        return new OwnerDTO(pModel.getId(),
                            pModel.getName(),
                            pModel.getLanguage(),
                            pModel.getCreated(),
                            pModel.getCreatedBy(),
                            pModel.getUpdated(),
                            pModel.getUpdatedBy());
    }

    public boolean hasUpdates() {
        return((getName() != null) || (getLanguage() != null));
    }

    public OwnerModel toModel(final int pid) {
        val model = new OwnerModel();
        model.setId(pid);
        model.setName(getName());
        model.setLanguage(getLanguage());

        return model;
    }

    public OwnerModel toModel(final OwnerModel pModel, final String pUpdatedBy) {
        val model = new OwnerModel();
        model.setId(pModel.getId());
        model.setCreated(pModel.getCreated());
        model.setCreatedBy(pModel.getCreatedBy());
        model.setUpdated(new Date());
        model.setUpdatedBy(pUpdatedBy);

        model.setName((getName() == null) ? pModel.getName() : getName());
        model.setLanguage((getLanguage() == null) ? pModel.getLanguage() : getLanguage());

        return model;
    }
}