package com.meritamerica.assignment7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.dto.TransactionDto;
import com.meritamerica.assignment7.service.TransactionService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/transfer")
	public String transfer(@RequestBody TransactionDto transaction ) {
		double amount = transaction.getAmount();
		transactionService.transfer(transaction.getSourceAccountNum(), transaction.getTargetAccountNum(), amount);
		return "Amount posted successfully";
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/deposit")
	public boolean deposit(@RequestBody TransactionDto transaction ) {
		double amount = transaction.getAmount();
		String type = transaction.getTransactionType();
		transactionService.deposit(transaction.getTargetAccountNum(), amount, type);
		return true;
	}
}
