package com.atomic.pafyplatform.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionType {
	DEPOSIT("deposit"), WITHDRAW("Withdraw");
	
	private String transactionType;
}