package com.meritamerica.assignment6.service;

import java.util.List;

import com.meritamerica.assignment6.models.AccountHolder;
import com.meritamerica.assignment6.models.CheckingAccount;

public interface AccountHolderService {
	public AccountHolder addAccountHolder(AccountHolder accountHolder);
	public AccountHolder getAccountHolder(int id);
	public List<AccountHolder> getAccountHolders();
	public List<CheckingAccount> getCheckingAccounts(int id);
}
