package com.ta.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ta.enums.RoleType;
import com.ta.models.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

	Optional<Role> findByName(RoleType name);
	
	Boolean existsByName(RoleType name);
}
