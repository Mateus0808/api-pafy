package com.atomic.pafyplatform.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atomic.pafyplatform.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
	
	Optional<Transaction> findById(UUID id);
	
	@Query("SELECT t FROM Transaction t WHERE t.user.id = :userId")
	List<Transaction> findByUserId(@Param("userId") UUID userId);
	
}
