package com.meritamerica.assignment7.service;

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

public interface AccountsService {

	public PersonalCheckingAccount addPersonalCheckingAccount(int id, PersonalCheckingAccount checkingAccount) 
			throws NoSuchAccountException, 
			ExceedsCombinedBalanceLimitException, 
			InvalidArgumentException, ReachedAccountLimitException;
	
	public DBACheckingAccount addDBACheckingAccount(int id, DBACheckingAccount dbaCheckingAccount) 
			throws NoResourceFoundException, 
			ExceedsCombinedBalanceLimitException, 
			InvalidArgumentException, ReachedAccountLimitException, NoSuchAccountException;
	
	public SavingsAccount addSavingsAccount(int id, SavingsAccount savingsAccount) 
			throws NoResourceFoundException, 
			ExceedsCombinedBalanceLimitException, 
			InvalidArgumentException, ReachedAccountLimitException;

	public CDAccount addCDAccount(int id, CDAccount cdAccount) 
			throws ExceedsCombinedBalanceLimitException, 
			InvalidArgumentException, NoResourceFoundException;
	
	public RegularIRA addRegularIRA(int id, RegularIRA regularIRA) 
			throws ExceedsCombinedBalanceLimitException, 
			InvalidArgumentException, NoResourceFoundException, ReachedAccountLimitException;

	public boolean closeAccount(int accountNumber) throws InvalidArgumentException;
	
}
