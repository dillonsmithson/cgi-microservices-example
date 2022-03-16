package com.cgi.glk.ectp.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PetDTO {

    private int id;
    private String name;
    private int ownerId;
    private String type;
    private String breed;
    private String gender;
    private Date dob;
    private String color;
    private double weight; //need 16 decimal points of precision??
}
