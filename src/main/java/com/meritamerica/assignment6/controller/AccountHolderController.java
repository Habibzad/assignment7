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

import com.meritamerica.assignment6.models.AccountHolder;
import com.meritamerica.assignment6.models.AccountHoldersContactDetails;
import com.meritamerica.assignment6.service.AccountHolderService;

@RestController
public class AccountHolderController {
	Logger logs = LoggerFactory.getLogger(AccountHolderController.class);
	
	@Autowired
	private AccountHolderService accountHolderService;
	
	@PostMapping("/accountholders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
		return accountHolderService.addAccountHolder(accountHolder);
	}
	
	@GetMapping("/accountholders")
	public List<AccountHolder> getAccountHolders(){
		return accountHolderService.getAccountHolders();
	}
	
	@GetMapping("/accountholders/{id}")
	public AccountHolder getAccountHolders(@PathVariable("id") int id){
		return accountHolderService.getAccountHolder(id);
	}
	
	@PostMapping("/accountholders/{id}/contactdetails")
	public AccountHoldersContactDetails addDetails(@PathVariable("id") int id, @RequestBody AccountHoldersContactDetails accountDetails) {
		return accountHolderService.addContactDetails(id, accountDetails);
	}
}
