package com.atomic.pafyplatform.responses;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {
	private Long id;
	
	private String title;
	
	private BigDecimal value;
	
	private String category;
	
	private String transactionType;
	
	private Date createdAt;
	
	private Date updatedAt;
}
