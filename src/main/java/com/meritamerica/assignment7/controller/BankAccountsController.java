package com.meritamerica.assignment7.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment7.exceptions.InvalidArgumentException;
import com.meritamerica.assignment7.exceptions.NoSuchAccountException;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.CheckingAccount;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.repository.CheckingAccountRepo;
import com.meritamerica.assignment7.service.AccountHolderService;
import com.meritamerica.assignment7.service.AccountsService;

@RestController
@RequestMapping("/api")
public class BankAccountsController {
	Logger logs = LoggerFactory.getLogger(BankAccountsController.class);

	@Autowired
	private AccountsService accountsService;

	@Autowired
	private CheckingAccountRepo checkingAccountRepo;
	
	@Autowired
	private AccountHolderService accountHolderService;

	@PostMapping("/accountholders/{id}/checkingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public CheckingAccount addCheckingAccount(@PathVariable("id") int id, @RequestBody CheckingAccount checkingAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException, InvalidArgumentException {
		return accountsService.addCheckingAccount(id, checkingAccount);
	}

	@GetMapping("/accountholders/{id}/checkingAccounts") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<CheckingAccount> getCheckingAccounts(@PathVariable("id") int id) throws InvalidArgumentException{
		return accountHolderService.getCheckingAccounts(id);
	}

	@PostMapping("/accountholders/{id}/savingsAccounts")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSavingsAccount(@PathVariable("id") int id, @RequestBody SavingsAccount savingsAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException, InvalidArgumentException {
		return accountsService.addSavingsAccount(id, savingsAccount);
	}
	
	@GetMapping("/accountholders/{id}/savingsAccounts")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<SavingsAccount> getSavingsAccount(@PathVariable("id") int id) {
		return accountHolderService.getSavingsAccounts(id);
	}

	@PostMapping("/accountholders/{id}/cdAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public CDAccount addCDAccount(@PathVariable("id") int id, @RequestBody CDAccount cdAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException, InvalidArgumentException {
		return accountsService.addCDAccount(id, cdAccount);
	}
	
	@GetMapping("/accountholders/{id}/cdAccounts")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<CDAccount> getCDAccountS(@PathVariable("id") int id) {
		return accountHolderService.getCDAccounts(id);
	}
	
	@GetMapping("/checkingaccounts")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<CheckingAccount> getCheckingAccounts(){
		return checkingAccountRepo.findAll();
	}
}
