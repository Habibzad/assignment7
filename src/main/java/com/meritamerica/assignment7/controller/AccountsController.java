package com.meritamerica.assignment7.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.meritamerica.assignment7.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment7.exceptions.ExceptionResponse;
import com.meritamerica.assignment7.exceptions.InvalidArgumentException;
import com.meritamerica.assignment7.exceptions.NoSuchAccountException;
import com.meritamerica.assignment7.exceptions.ReachedAccountLimitException;
import com.meritamerica.assignment7.models.BankAccount;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.PersonalCheckingAccount;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.repository.BankAccountRepo;
import com.meritamerica.assignment7.repository.PersonalCheckingAccountRepo;
import com.meritamerica.assignment7.service.AccountHolderService;
import com.meritamerica.assignment7.service.AccountsService;


@ControllerAdvice
@RestController
@RequestMapping("/api")
@CrossOrigin
public class AccountsController {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	Logger logs = LoggerFactory.getLogger(AccountsController.class);

	@Autowired
	private AccountsService accountsService;
	
	@Autowired
	private BankAccountRepo bankAccountRepo;

	@Autowired
	private PersonalCheckingAccountRepo checkingAccountRepo;
	
	@Autowired
	private AccountHolderService accountHolderService;

	@PostMapping("/accountholders/{id}/checkingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public PersonalCheckingAccount addCheckingAccount(@PathVariable("id") int id, @RequestBody PersonalCheckingAccount checkingAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException, InvalidArgumentException {
		return accountsService.addCheckingAccount(id, checkingAccount);
	}

	@GetMapping("/accountholders/{id}/checkingAccounts") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<PersonalCheckingAccount> getCheckingAccounts(@PathVariable("id") int id) throws InvalidArgumentException{
		return accountHolderService.getPersonalCheckingAccounts(id);
	}

	@PostMapping("/accountholders/{id}/savingsAccounts")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSavingsAccount(@PathVariable("id") int id, @RequestBody SavingsAccount savingsAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException, InvalidArgumentException, ReachedAccountLimitException {
		return accountsService.addSavingsAccount(id, savingsAccount);
	}
	
	@GetMapping("/accountholders/{id}/savingsAccounts")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<SavingsAccount> getSavingsAccount(@PathVariable("id") int id) {
		return accountHolderService.getSavingsAccount(id);
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
	public List<PersonalCheckingAccount> getCheckingAccounts(){
		return checkingAccountRepo.findAll();
	}
	
	@GetMapping("/all-accounts")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<BankAccount> getAllAccounts(){
		return bankAccountRepo.findAll();
	}
	
	@PutMapping("/close-account/{accountNumber}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean closeAccount(@PathVariable int accountNumber) throws InvalidArgumentException {
		return accountsService.closeAccount(accountNumber);
	}
	
}
