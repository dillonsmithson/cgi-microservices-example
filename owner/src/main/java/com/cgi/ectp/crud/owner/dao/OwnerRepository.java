package com.cgi.ectp.crud.owner.dao;

import com.cgi.ectp.crud.owner.model.OwnerModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends CrudRepository<OwnerModel, Integer> {

    @Query( value = "SELECT * FROM owner WHERE (owner_name = :pName)",
            nativeQuery = true  )
    List<OwnerModel> byName(final String pName);
}
/*
CREATE TABLE owner ( owner_id SERIAL PRIMARY KEY,
                     owner_name TEXT NOT NULL,
                     owner_lang TEXT NOT NULL,
                     owner_created TIMESTAMP WITH TIME ZONE,
                     owner_createdby TEXT,
                     owner_updated TIMESTAMP WITH TIME ZONE,
                     owner_updatedby TEXT );
 */