package com.meritamerica.assignment7.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.exceptions.InvalidArgumentException;
import com.meritamerica.assignment7.exceptions.NoResourceFoundException;
import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.ContactDetails;
import com.meritamerica.assignment7.service.AccountHolderService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AccountHolderController {
	Logger logs = LoggerFactory.getLogger(AccountHolderController.class);

	@Autowired
	private AccountHolderService accountHolderService;

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String home() throws InvalidArgumentException {
//		return "hello";
		throw new InvalidArgumentException("this is a test exception message");
	}
	
	@PostMapping("/accountholders")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AccountHolder addAccountHolder(@RequestBody AccountHolder accountHolder) {
		return accountHolderService.addAccountHolder(accountHolder);
	}

	@GetMapping("/accountholders")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<AccountHolder> getAccountHolders() {
		return accountHolderService.getAccountHolders();
	}

	@GetMapping("/accountholders/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AccountHolder getAccountHolder(@PathVariable("id") int id) {
		return accountHolderService.getAccountHolder(id);
	}
	
	//Update Account Holder
	@PutMapping("/accountholders/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AccountHolder updateAccountHolder(@RequestBody AccountHolder accountHolder) throws NoResourceFoundException {
		return accountHolderService.updateAccountHolder(accountHolder);
	}
	
	@DeleteMapping("/accountholders")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AccountHolder deleteAccountHolder(@RequestBody AccountHolder accountHolder) throws NoResourceFoundException {
		return accountHolderService.deleteAccountHolder(accountHolder);
	}
	
	@PostMapping("/accountholders/{id}/contactdetails")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ContactDetails addDetails(@PathVariable("id") int id,
			@RequestBody ContactDetails contactDetails) {
		return accountHolderService.addContactDetails(id, contactDetails);
	}
}
