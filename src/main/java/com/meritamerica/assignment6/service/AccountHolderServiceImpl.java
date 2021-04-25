package com.meritamerica.assignment6.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment6.models.AccountHolder;
import com.meritamerica.assignment6.models.CheckingAccount;
import com.meritamerica.assignment6.repository.AccountHolderRepo;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {
	
	@Autowired
	private AccountHolderRepo accountHolderRepo;
	
	@Override
	public AccountHolder addAccountHolder(AccountHolder accountHolder) {
		return accountHolderRepo.save(accountHolder);
	}

	@Override
	public List<AccountHolder> getAccountHolders() {
		return accountHolderRepo.findAll();
	}

	@Override
	public AccountHolder getAccountHolder(int id) {
		return accountHolderRepo.getOne(id);
	}

	@Override
	public List<CheckingAccount> getCheckingAccounts(int id) {
		AccountHolder accountHolder = accountHolderRepo.getOne(id);
		return accountHolder.getCheckingAccounts();
	}
}
