package com.atomic.pafyplatform.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atomic.pafyplatform.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
    void deleteByEmail(String email);

    Optional<User> findByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(String username);
    
}
