package com.atomic.pafyplatform.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.atomic.pafyplatform.exceptions.TransactionNotFoundException;
import com.atomic.pafyplatform.models.Transaction;
import com.atomic.pafyplatform.models.User;
import com.atomic.pafyplatform.repositories.TransactionRepository;
import com.atomic.pafyplatform.request.CreateTransactionDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

	private final TransactionRepository transactionRepository;
	
	private final IUserService userService;
	
	@Override
	public Transaction getTransactionById(UUID id) {
		return this.transactionRepository.findById(id)
				.orElseThrow(() -> new TransactionNotFoundException("Transação não encontrada"));
	}

	@Override
	public List<Transaction> listUserTransactions(UUID userId) {
		return this.transactionRepository.findByUserId(userId);
	}

	@Override
	public void updateTransaction(UUID id, Transaction updatedTransaction) {
		// TODO Auto-generated method stub
	}

	@Transactional
	@Override
	public void deleteTransaction(UUID id) {
		boolean transaction = this.transactionRepository.findById(id).isPresent();			
		if(!transaction) {
			throw new TransactionNotFoundException("Transação não encontrada");
		}
		this.transactionRepository.deleteById(id);
	}

	@Override
	public Transaction createTransaction(String email, @Valid CreateTransactionDTO data) {
		User user = this.userService.getUser(email);
		Transaction transaction = new Transaction(
				user, data.getTitle(), data.getValue(), 
				data.getCategory(), data.getTransactionType(), data.getCreatedAt());
		return this.transactionRepository.save(transaction);
	}

}
