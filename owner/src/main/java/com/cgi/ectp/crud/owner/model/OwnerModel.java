package com.cgi.ectp.crud.owner.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/*
Models should not have equals and hashcode
 */
@Entity
@Table(name = "owner")
@Getter
@Setter
@ToString
public class OwnerModel {

    @Column(name = "owner_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "owner_name")
    private String name;

    @Column(name = "owner_lang")
    private String language;

    @Column(name = "owner_created")
    private Date created;

    @Column(name = "owner_created_by")
    private String createdBy;

    @Column(name = "owner_updated")
    private Date updated;

    @Column(name = "owner_updated_by")
    private String updatedBy;
}
