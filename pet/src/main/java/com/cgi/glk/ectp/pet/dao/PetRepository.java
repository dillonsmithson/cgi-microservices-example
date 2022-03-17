package com.cgi.glk.ectp.pet.dao;

import com.cgi.glk.ectp.pet.model.PetModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<PetModel, Integer> {

    @Query( value = "SELECT * FROM pet WHERE (pet_name = :pName)",
            nativeQuery = true)
    List<PetModel> byName(final String pName);
}

/*
CREATE TABLE pet ( pet_id SERIAL PRIMARY KEY,
				   pet_name TEXT NOT NULL,
				   pet_owner_id INTEGER REFERENCES owner (owner_id),
				   pet_type TEXT NOT NULL,
				   pet_breed TEXT NOT NULL,
				   pet_gender TEXT NOT NULL,
				   pet_color TEXT NOT NULL,
				   pet_dob DATE,
				   pet_current_weight NUMERIC );
 */
