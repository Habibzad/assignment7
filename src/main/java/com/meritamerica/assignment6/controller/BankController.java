package com.meritamerica.assignment6.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment6.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment6.exceptions.InvalidArgumentException;
import com.meritamerica.assignment6.exceptions.NoSuchAccountException;
import com.meritamerica.assignment6.models.AccountHolder;
import com.meritamerica.assignment6.models.CDAccount;
import com.meritamerica.assignment6.models.CDOffering;
import com.meritamerica.assignment6.models.CheckingAccount;
import com.meritamerica.assignment6.models.SavingsAccount;
import com.meritamerica.assignment6.repository.AccountHolderRepo;

@RestController
public class BankController {
	Logger logs = LoggerFactory.getLogger(BankController.class);

	@Autowired
	private AccountHolderRepo accountHolderRepo;
	
	@GetMapping("/")
	public String home() {
		return "Welcome";
	}
	
	@GetMapping("/accountholders")
	public List<AccountHolder> getAccountHolders(){
		return accountHolderRepo.findAll();
	}
	
	@PostMapping("/accountholders")
	public AccountHolder addAccountHolder(@RequestBody AccountHolder accountHolder) {
		accountHolderRepo.save(accountHolder);
		return accountHolder;
	}

}
