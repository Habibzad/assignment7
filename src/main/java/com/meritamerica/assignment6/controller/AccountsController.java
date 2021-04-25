package com.meritamerica.assignment6.controller;

import java.util.List;

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
import com.meritamerica.assignment6.models.CDAccount;
import com.meritamerica.assignment6.models.CheckingAccount;
import com.meritamerica.assignment6.models.SavingsAccount;
import com.meritamerica.assignment6.service.AccountHolderService;
import com.meritamerica.assignment6.service.AccountsService;

@RestController
public class AccountsController {
	Logger logs = LoggerFactory.getLogger(AccountsController.class);

	@Autowired
	private AccountsService accountsService;
	
	@Autowired
	private AccountHolderService accountHolderService;
	
	@PostMapping("/accountholders/{id}/checkingAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount addCheckingAccount(@PathVariable ("id") int id, @RequestBody CheckingAccount checkingAccount) 
			throws ExceedsCombinedBalanceLimitException, 
			NoSuchAccountException, 
			InvalidArgumentException {
		return accountsService.addCheckingAccount(id, checkingAccount);
	}
	
	@GetMapping("/accountholders/{id}/checkingAccount")
	public List<CheckingAccount> getCheckingAcconts(@PathVariable("id") int id){
		return accountHolderService.getCheckingAccounts(id);
	}
	
	@PostMapping("/accountholders/{id}/savingsAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSavingsAccount(@PathVariable ("id") int id, @RequestBody SavingsAccount savingsAccount) 
			throws ExceedsCombinedBalanceLimitException, 
			NoSuchAccountException,
			InvalidArgumentException{
		return accountsService.addSavingsAccount(id, savingsAccount);
	}
	
	@PostMapping("/accountholders/{id}/cdAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addCDAccount(@PathVariable ("id") int id, @RequestBody CDAccount cdAccount) 
			throws ExceedsCombinedBalanceLimitException, 
			NoSuchAccountException,
			InvalidArgumentException{
		return accountsService.addCDAccount(id, cdAccount); 
	}


}
