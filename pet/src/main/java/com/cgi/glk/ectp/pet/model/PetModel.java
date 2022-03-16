package com.cgi.glk.ectp.pet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pet")
@Getter
@Setter
@ToString
public class PetModel {

    @Column(name = "pet_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pet_name")
    private String name;

    @Column(name = "pet_owner_id")
    private int ownerId;

    @Column(name = "pet_type")
    private String type;

    @Column(name = "pet_breed")
    private String breed;

    @Column(name = "pet_breed")
    private String gender;

    @Column(name = "pet_color")
    private String color;

    @Column(name = "pet_dob")
    private Date dob;

    @Column(name = "pet_current_weight")
    private float currentWeight;
}
