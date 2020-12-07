package com.southernartsoap.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.southernartsoap.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByRole(String role);
}