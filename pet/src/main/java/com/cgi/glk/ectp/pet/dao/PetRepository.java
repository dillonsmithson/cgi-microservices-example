package com.cgi.glk.ectp.pet.dao;

import com.cgi.glk.ectp.pet.dto.PetDTO;
import com.cgi.glk.ectp.pet.model.PetModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<PetModel, Integer> {

    @Query( value = "SELECT * FROM pet WHERE pet_owner_id = :pOwnerId ORDER BY pet_id ASC", nativeQuery = true)
    Collection<PetModel> byOwner(final Integer pOwnerId);
}
