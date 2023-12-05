package com.atomic.pafyplatform.services;

import java.util.List;
import java.util.UUID;

import com.atomic.pafyplatform.models.Transaction;
import com.atomic.pafyplatform.request.CreateTransactionDTO;

import jakarta.validation.Valid;

public interface ITransactionService {

	Transaction getTransactionById(UUID id);

	List<Transaction> listUserTransactions(UUID user_id);

	void updateTransaction(UUID id, Transaction updatedTransaction);

	void deleteTransaction(UUID id);

	Transaction createTransaction(String email, @Valid CreateTransactionDTO data);
}
