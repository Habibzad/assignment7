package com.meritamerica.assignment7.service;

import java.util.List;

import com.meritamerica.assignment7.exceptions.InvalidArgumentException;
import com.meritamerica.assignment7.exceptions.NoResourceFoundException;
import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.PersonalCheckingAccount;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.models.User;

public interface AccountHolderService {
	public AccountHolder addAccountHolder(AccountHolder accountHolder);
	public AccountHolder getAccountHolder(int id);
	public List<AccountHolder> getAccountHolders();
	public AccountHolder deleteAccountHolder(int id) throws NoResourceFoundException;
	
	public List<PersonalCheckingAccount> getPersonalCheckingAccounts(int id) throws InvalidArgumentException;
	public List<SavingsAccount> getSavingsAccount(int id);
	public List<CDAccount> getCDAccounts(int id);
	public AccountHolder updateAccountHolder(int id, AccountHolder accountHolder) throws NoResourceFoundException;
	
	public User setUser(int id, User user) throws NoResourceFoundException;
}
