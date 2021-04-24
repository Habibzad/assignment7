package com.meritamerica.assignment6.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment6.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment6.models.AccountHolder;
import com.meritamerica.assignment6.models.CheckingAccount;
import com.meritamerica.assignment6.service.MeritBankService;

@RestController
public class BankController {
	Logger logs = LoggerFactory.getLogger(BankController.class);

	@Autowired
	private MeritBankService meritBankService;
	
	@GetMapping("/")
	public String home() {
		return "Welcome";
	}
	
	@GetMapping("/accountholders")
	public List<AccountHolder> getAccountHolders(){
		return meritBankService.getAccountHolders();
	}
	
	@PostMapping("/accountholders")
	public AccountHolder addAccountHolder(@RequestBody AccountHolder accountHolder) {
		return meritBankService.addAccountHolder(accountHolder);
	}
	
	@PostMapping("/accountholders/{id}/checkingAccount")
	public CheckingAccount addCheckingAccount(@PathVariable ("id") int id, @RequestBody CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException {
		CheckingAccount checkAcc = new CheckingAccount(checkingAccount.getBalance());
		meritBankService.addCheckingAccount(id, checkingAccount);
		return checkAcc;
	}

}
