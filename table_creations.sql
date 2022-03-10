CREATE TABLE owner ( owner_id SERIAL PRIMARY KEY,
				     owner_name TEXT NOT NULL,
					 owner_lang TEXT NOT NULL,
				     owner_created TIMESTAMP WITH TIME ZONE,
				     owner_createdby TEXT,
				     owner_updated TIMESTAMP WITH TIME ZONE,
				     owner_updatedby TEXT );

CREATE TABLE pet ( pet_id SERIAL PRIMARY KEY,
				   pet_name TEXT NOT NULL, 
				   pet_owner_id INTEGER REFERENCES owner (owner_id),
				   pet_type TEXT NOT NULL,
				   pet_breed TEXT NOT NULL,
				   pet_gender TEXT NOT NULL,
				   pet_color TEXT NOT NULL, 
				   pet_dob DATE,
				   pet_current_weight NUMERIC );

CREATE TABLE picture ( picture_id SERIAL PRIMARY KEY,
					   picture_parent_type TEXT NOT NULL,
					   picture_parent_id INTEGER NOT NULL,
					   picture_primary BOOLEAN NOT NULL,
					   picture_description TEXT NOT NULL,
					   picture_source TEXT NOT NULL );

CREATE TABLE jwt ( jwt_username TEXT NOT NULL PRIMARY KEY,
				   jwt_jwt TEXT NOT NULL,
				   jwt_hash Integer NOT NULL )					   