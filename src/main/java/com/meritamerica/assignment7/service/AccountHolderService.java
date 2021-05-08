package com.meritamerica.assignment7.service;

import java.util.List;

import com.meritamerica.assignment7.exceptions.InvalidArgumentException;
import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.AccountHoldersContactDetails;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.CheckingAccount;
import com.meritamerica.assignment7.models.SavingsAccount;

public interface AccountHolderService {
	public AccountHolder addAccountHolder(AccountHolder accountHolder);
	public AccountHolder getAccountHolder(int id);
	public List<AccountHolder> getAccountHolders();
	public List<CheckingAccount> getCheckingAccounts(int id) throws InvalidArgumentException;
	public List<SavingsAccount> getSavingsAccounts(int id);
	public List<CDAccount> getCDAccounts(int id);
	public AccountHoldersContactDetails addContactDetails(int id, AccountHoldersContactDetails contactDetails);
}
