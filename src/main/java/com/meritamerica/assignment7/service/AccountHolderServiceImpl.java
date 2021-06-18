package com.meritamerica.assignment7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.exceptions.InvalidArgumentException;
import com.meritamerica.assignment7.exceptions.NoResourceFoundException;
import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.PersonalCheckingAccount;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.models.User;
import com.meritamerica.assignment7.repository.AccountHolderRepo;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

	@Autowired
	private AccountHolderRepo accountHolderRepo;

	@Override
	public AccountHolder addAccountHolder(AccountHolder accountHolder) {
		accountHolderRepo.save(accountHolder);
		return accountHolder;
	}
	
	//update account holder
	@Override
	public AccountHolder updateAccountHolder(int id, AccountHolder accountHolder) throws NoResourceFoundException {
		AccountHolder accHold = accountHolderRepo.getOne(id);
		accHold = accountHolder;
		accHold.setId(id);
		return accountHolderRepo.save(accHold);
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
	public List<PersonalCheckingAccount> getPersonalCheckingAccounts(int id) throws InvalidArgumentException{
		if(accountHolderRepo.existsById(id)) {
			AccountHolder accountHolder = accountHolderRepo.getOne(id);
			return null;
		}
		throw new InvalidArgumentException("No such account holder exist");
	}

	@Override
	public List<SavingsAccount> getSavingsAccount(int id) {
		AccountHolder accountHolder = accountHolderRepo.getOne(id);
		return accountHolder.getSavingsAccounts();
	}

	@Override
	public List<CDAccount> getCDAccounts(int id) {
		AccountHolder accountHolder = accountHolderRepo.getOne(id);
		return accountHolder.getCdAccounts();
	}
	
	@Override
	public AccountHolder deleteAccountHolder(int id) throws NoResourceFoundException{
		AccountHolder ach = accountHolderRepo.getOne(id);
		if(ach!=null) {
			accountHolderRepo.delete(ach);
			return ach;
		} 
		throw new NoResourceFoundException("Account Does Not Exist");
	}

//	@Override
//	public ContactDetails addContactDetails(int id, ContactDetails contactDetails) {
//		AccountHolder accountHolder = accountHolderRepo.getOne(id);
//		contactDetails.setAccountHolder(accountHolder);
//		contactDetailsRepo.save(contactDetails);
//		return contactDetails;
//	}

	@Override
	public User setUser(int id, User user) throws NoResourceFoundException{
		AccountHolder ach = accountHolderRepo.getOne(id);
		if(ach!=null) {
			ach.setUser(user);
			accountHolderRepo.save(ach);
			return user;
		}
		throw new NoResourceFoundException("AccountHolder not found");
	}
	
}
