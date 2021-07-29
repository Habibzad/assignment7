package com.meritamerica.assignment7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.dto.TransactionDto;
import com.meritamerica.assignment7.models.Transaction;
import com.meritamerica.assignment7.repository.TransactionRepo;
import com.meritamerica.assignment7.service.TransactionService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	@GetMapping("/transactions")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Transaction> getAllAccounts(){
		return transactionRepo.findAll();
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/transfer")
	public String transfer(@RequestBody TransactionDto transaction ) {
		double amount = transaction.getAmount();
		transactionService.transfer(transaction.getSourceAccountNum(), transaction.getTargetAccountNum(), transaction.getDescription(), amount);
		return "Amount posted successfully";
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/deposit")
	public boolean deposit(@RequestBody TransactionDto transaction ) {
		double amount = transaction.getAmount();
		transactionService.deposit(transaction.getTargetAccountNum(), transaction.getDescription(), amount);
		return true;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/withdraw")
	public boolean withdraw(@RequestBody TransactionDto transaction ) {
		double amount = transaction.getAmount();
		transactionService.withdraw(transaction.getTargetAccountNum(), transaction.getDescription(), amount);
		return true;
	}
}
