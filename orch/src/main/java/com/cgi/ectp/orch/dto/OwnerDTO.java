package com.cgi.ectp.orch.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OwnerDTO {

    private int id;
    private String name;
    private String language;
    private Date created;
    private String createdBy;
    private Date updated;
    private String updatedBy;
}