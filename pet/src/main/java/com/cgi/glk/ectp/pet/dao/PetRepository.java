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


