package com.cgi.etcp.crud.owner.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "owner")
@Getter
@Setter
@ToString
public class OwnerModel {

    @Column(name = "owner_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "owner_name")
    private String name;

    @Column(name = "owner_lang")
    private String language;

    @Column(name = "owner_created")
    private Date created;

    @Column(name = "owner_createdby")
    private String createdBy;

    @Column(name = "owner_updated")
    private Date updated;

    @Column(name = "owner_updatedby")
    private String updatedBy;
}

/*  Pet Database SQL
*   Putting it here, so I don't lose it in the future.
*
*   CREATE TABLE owner ( owner_id SERIAL PRIMARY KEY,
*				         owner_name TEXT NOT NULL,
*					     owner_lang TEXT NOT NULL,
*				         owner_created TIMESTAMP WITH TIME ZONE,
*				         owner_createdby TEXT,
*				         owner_updated TIMESTAMP WITH TIME ZONE,
*				         owner_updatedby TEXT );
*
*   CREATE TABLE pet ( pet_id SERIAL PRIMARY KEY,
*				       pet_name TEXT NOT NULL,
*				       pet_owner_id INTEGER REFERENCES owner (owner_id),
*				       pet_type TEXT NOT NULL,
*				       pet_breed TEXT NOT NULL,
*				       pet_gender TEXT NOT NULL,
*				       pet_color TEXT NOT NULL,
*				       pet_dob DATE,
*				       pet_current_weight NUMERIC );
*
*   CREATE TABLE picture ( picture_id SERIAL PRIMARY KEY,
*					       picture_parent_type TEXT NOT NULL,
*					       picture_parent_id INTEGER NOT NULL,
*					       picture_primary BOOLEAN NOT NULL,
*					       picture_description TEXT NOT NULL,
*					       picture_source TEXT NOT NULL );
*/
