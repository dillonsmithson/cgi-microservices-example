package com.cgi.glk.ectp.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jwt")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTModel {

    @Column(name = "jwt_username")
    @Id
    private String username;

    @Column(name = "jwt_jwt")
    private String jwt;

    @Column(name = "jwt_hash")
    private int hash;

    public static JWTModel of(final String pUsername, final String pJWT) {
        return new JWTModel(pUsername, pJWT, pJWT.hashCode());
    }
}

/*
CREATE TABLE jwt ( jwt_username TEXT NOT NULL PRIMARY KEY,
				   jwt_jwt TEXT NOT NULL,
				   jwt_hash Integer NOT NULL )
 */