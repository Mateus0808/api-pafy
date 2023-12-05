package com.atomic.pafyplatform.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atomic.pafyplatform.models.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
	  Optional<Role> findByName(String role);

	  boolean existsByName(String role);
}
