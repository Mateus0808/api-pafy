package com.atomic.pafyplatform.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atomic.pafyplatform.exceptions.TransactionNotFoundException;
import com.atomic.pafyplatform.models.Transaction;
import com.atomic.pafyplatform.models.TransactionType;
import com.atomic.pafyplatform.request.CreateTransactionDTO;
import com.atomic.pafyplatform.responses.ResponseMessage;
import com.atomic.pafyplatform.responses.TransactionResponse;
import com.atomic.pafyplatform.services.ITransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

	private final ITransactionService transactionService;
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> listUserTransactions(@PathVariable UUID userId) {
        List<Transaction> transactions = transactionService.listUserTransactions(userId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable UUID id) {
        try {
            Transaction transaction = this.transactionService.getTransactionById(id);
            return ResponseEntity.ok(transaction);
        } catch (TransactionNotFoundException ex) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(ex.getMessage(), HttpStatus.NOT_FOUND, false));
        } catch (Exception ex) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Erro ao buscar transação", HttpStatus.INTERNAL_SERVER_ERROR, false));
        }
    }
	
	@PostMapping("/create-transaction/{email}")
    public ResponseEntity<?> createTransaction(@PathVariable String email, @RequestBody @Valid CreateTransactionDTO data) {
        try {
        	Transaction transaction = transactionService.createTransaction(email, data);
            return ResponseEntity.ok(transaction);
        } catch (Exception ex) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(ex.getMessage(), HttpStatus.BAD_REQUEST, false));
        }
    }
	
//	@PutMapping("/{transactionId}")
//    public ResponseEntity<TransactionResponse> updateTransaction(
//    		@PathVariable Long transactionId, 
//    		@RequestParam(required = false) String title,
//    		@RequestParam(required = false) BigDecimal value,
//    		@RequestParam(required = false) String category,
//    		@RequestParam(required = false) TransactionType transactionType) {
//        try {
//            transactionService.updateTransaction(transactionId, updatedTransaction);
//            return ResponseEntity.ok().body("Transação atualizada com sucesso");
//        } catch (IllegalArgumentException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<?> deleteTransaction(@PathVariable UUID id) {
	    try {
	        transactionService.deleteTransaction(id);
	        return ResponseEntity.ok().body(new ResponseMessage("Transação excluída com sucesso", HttpStatus.OK, true));
	    } catch (TransactionNotFoundException ex) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(ex.getMessage(), HttpStatus.NOT_FOUND, false));
	    } catch (Exception ex) {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Erro ao deletar transação: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, false));
	    }
	}
}
