package com.cgi.glk.ectp.pet.dao;

import com.cgi.glk.ectp.pet.model.PetModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<PetModel, Integer> {
}
