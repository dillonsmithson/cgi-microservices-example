package com.cgi.glk.ectp.auth.repository;

import com.cgi.glk.ectp.auth.model.JWTModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JWTRepository extends CrudRepository<JWTModel, String> {

    @Query( value = "SELECT* FROM jwt WHERE (jwt_hash = :pHash)",
            nativeQuery = true  )
    List<JWTModel> byHash(final int pHash);
}
