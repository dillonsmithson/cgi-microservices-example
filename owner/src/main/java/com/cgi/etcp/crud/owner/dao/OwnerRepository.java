package com.cgi.etcp.crud.owner.dao;

import com.cgi.etcp.crud.owner.model.OwnerModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface OwnerRepository extends CrudRepository<OwnerModel, Integer> {

    @Query( value = "SELECT * FROM owner WHERE (owner_name = :pName)",
            nativeQuery = true  )
    List<OwnerModel> byName(final String pName);
}
