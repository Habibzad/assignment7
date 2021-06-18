package com.meritamerica.assignment7.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment7.exceptions.InvalidArgumentException;
import com.meritamerica.assignment7.exceptions.NoResourceFoundException;
import com.meritamerica.assignment7.exceptions.NoSuchAccountException;
import com.meritamerica.assignment7.exceptions.ReachedAccountLimitException;
import com.meritamerica.assignment7.models.BankAccount;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.DBACheckingAccount;
import com.meritamerica.assignment7.models.PersonalCheckingAccount;
import com.meritamerica.assignment7.models.RegularIRA;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.repository.BankAccountRepo;
import com.meritamerica.assignment7.repository.PersonalCheckingAccountRepo;
import com.meritamerica.assignment7.service.AccountHolderService;
import com.meritamerica.assignment7.service.AccountsService;

/**
 * Resources for getting, adding, updating, closing, and deleting accounts
 * @author Ahmad
 * 
 * Resources:
 * Add PersonalCheckingAccount
 * Get PersonalCheckingAccount by account number
 * Get PersonalCheckingAccounts
 * 
 */

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AccountsController {
	
	Logger logs = LoggerFactory.getLogger(AccountsController.class);

	@Autowired
	private AccountsService accountsService;
	
	@Autowired
	private BankAccountRepo bankAccountRepo;

	@Autowired
	private PersonalCheckingAccountRepo personalCheckingAccountRepo;
	
	@Autowired
	private AccountHolderService accountHolderService;

	
	/**
	 * Add personal checking account, given the data provided
	 * @param id
	 * @return Personal Checking Account
	 * 
	 * Returns one of the following status codes:
	 * 201: successfully created a new personal checking account
	 * 400: unable to create the account because it exceeds the maximum combined balance allowed
	 * 404: unable to locate the account holder
	 * 403: unable to create the account because the balance cannot be negative and
	 * 403: unable to create the account because the account holder can have only one personal checking account
	 */
	
	@PostMapping("/accountholders/{id}/personalchecking")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public PersonalCheckingAccount addPersonalCheckingAccount(@PathVariable("id") int id, @RequestBody PersonalCheckingAccount personalChecking)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException, InvalidArgumentException, ReachedAccountLimitException {
		return accountsService.addPersonalCheckingAccount(id, personalChecking);
	}

	/**
	 * Get list of personal checking accounts for a particular account holder, given the data provided
	 * @param id
	 * @return list PersonalCheckingAccount
	 * 
	 * Returns one of the following status codes:
	 * 200: successfully retrieved list of personal checking accounts
	 * 404: unable to locate the account holder
	 */
	@GetMapping("/accountholders/{id}/personalchecking") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<PersonalCheckingAccount> getPersonalCheckingAccounts(@PathVariable("id") int id) throws InvalidArgumentException, NoSuchAccountException{
		return accountHolderService.getPersonalCheckingAccounts(id);
	}
	
	/**
	 * Add DBA checking account, given the data provided
	 * @param id
	 * @return Personal Checking Account
	 * 
	 * Returns one of the following status codes:
	 * 201: successfully created a new personal checking account
	 * 400: unable to create the account because it exceeds the maximum combined balance allowed
	 * 404: unable to locate the account holder
	 * 403: unable to create the account because the balance cannot be negative and
	 * 403: unable to create the account because the account holder can have only one personal checking account
	 * @throws NoResourceFoundException 
	 */
	
	@PostMapping("/accountholders/{id}/dba-checking")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public DBACheckingAccount addDBACheckingAccount(@PathVariable("id") int id, @RequestBody DBACheckingAccount dbaChecking)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException, InvalidArgumentException, ReachedAccountLimitException, NoResourceFoundException {
		return accountsService.addDBACheckingAccount(id, dbaChecking);
	}

	/**
	 * Add savings account
	 * @param id
	 * @return Personal Checking Account
	 * 
	 * Returns one of the following status codes:
	 * 201: successfully created a new personal checking account
	 * 400: unable to create the account because it exceeds the maximum combined balance allowed
	 * 404: unable to locate the account holder
	 * 403: unable to create the account because the balance cannot be negative and
	 * 403: unable to create the account because the account holder can have only one savings account
	 */
	
	@PostMapping("/accountholders/{id}/savingsAccounts")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSavingsAccount(@PathVariable("id") int id, @RequestBody SavingsAccount savingsAccount)
			throws ExceedsCombinedBalanceLimitException, InvalidArgumentException, ReachedAccountLimitException, NoResourceFoundException {
		return accountsService.addSavingsAccount(id, savingsAccount);
	}
	
	/**
	 * Get list of savings accounts for a particular account holder
	 * @param id
	 * @return list SavingsAccount
	 * 
	 * Returns one of the following status codes:
	 * 200: successfully retrieved list of savings accounts
	 * 404: unable to locate the account holder
	 */
	
	@GetMapping("/accountholders/{id}/savingsAccounts")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<SavingsAccount> getSavingsAccount(@PathVariable("id") int id) {
		return accountHolderService.getSavingsAccount(id);
	}

	@PostMapping("/accountholders/{id}/cdAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public CDAccount addCDAccount(@PathVariable("id") int id, @RequestBody CDAccount cdAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException, InvalidArgumentException, NoResourceFoundException {
		return accountsService.addCDAccount(id, cdAccount);
	}
	
	@GetMapping("/accountholders/{id}/cdAccounts")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<CDAccount> getCDAccountS(@PathVariable("id") int id) {
		return accountHolderService.getCDAccounts(id);
	}
	
	@GetMapping("/personalchecking")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<PersonalCheckingAccount> getPersonalCheckingAccounts(){
		return personalCheckingAccountRepo.findAll();
	}
	
	@PostMapping("/accountholders/{id}/regular-ira")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public RegularIRA addRegularIRA(@PathVariable("id") int id, @RequestBody RegularIRA regularIRA)
			throws ExceedsCombinedBalanceLimitException, InvalidArgumentException, ReachedAccountLimitException, NoResourceFoundException {
		return accountsService.addRegularIRA(id, regularIRA);
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
