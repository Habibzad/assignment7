package com.meritamerica.assignment6.service;

import java.util.List;

import com.meritamerica.assignment6.models.AccountHolder;
import com.meritamerica.assignment6.models.CDAccount;
import com.meritamerica.assignment6.models.CDOffering;
import com.meritamerica.assignment6.models.CheckingAccount;
import com.meritamerica.assignment6.models.SavingsAccount;

public interface MeritBank {
	public List<CDOffering> getCDOfferings();

	public CDOffering getCDOffering(int id);

	public CDOffering addCDOffering(CDOffering cdOffering);

	public void clearCDOfferings();

	public List<AccountHolder> getAccountHolders();

	public AccountHolder getAccountHolder(int id);

	public AccountHolder addAccountHolder(AccountHolder accountHolder);

	public double getAllAccountsBalances();

	public CheckingAccount addCheckingAccount(int id, CheckingAccount checkingAccount);

	public SavingsAccount addSavingsAccount(int id, SavingsAccount savingsAccount);

	public CDAccount addCDAccount(int id, CDAccount cdAccount);
}
