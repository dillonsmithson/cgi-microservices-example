package com.cgi.glk.ectp.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PetDTO {

    private int id;
    private String name;
    private Integer ownerId;
    private String type;
    private String breed;
    private String gender;
    private String color;
    private Date dob;
    private Double currentWeight;
}
