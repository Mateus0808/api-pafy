package com.atomic.pafyplatform.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "title", nullable = false, length = 60)
	private String title;
	
	@Column(name = "value", nullable = false)
	private BigDecimal value;
	
	@Column(name = "category", nullable = false, length = 30)
	private String category;
	
	@Column(name = "transaction_type", nullable = false)
	private TransactionType transactionType;
	
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP) 
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
    
    public Transaction(User user, String title, BigDecimal value, String category, TransactionType type, Date createdAt) {
    	this.user = user;
    	this.title = title;
    	this.value = value;
    	this.category = category;
    	this.transactionType = type;
    	this.createdAt = createdAt;
    }
}
