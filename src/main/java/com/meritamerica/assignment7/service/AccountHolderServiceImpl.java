package com.meritamerica.assignment7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.exceptions.InvalidArgumentException;
import com.meritamerica.assignment7.exceptions.NoResourceFoundException;
import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.ContactDetails;
import com.meritamerica.assignment7.models.PersonalCheckingAccount;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.repository.AccountHolderRepo;
import com.meritamerica.assignment7.repository.ContactDetailsRepo;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

	@Autowired
	private AccountHolderRepo accountHolderRepo;
	
	@Autowired
	private ContactDetailsRepo contactDetailsRepo;

	@Override
	public AccountHolder addAccountHolder(AccountHolder accountHolder) {
		accountHolderRepo.save(accountHolder);
		if(accountHolder.getContactDetails()!=null) {
			accountHolder.getContactDetails().setAccountHolder(accountHolder);
			contactDetailsRepo.save(accountHolder.getContactDetails());
		}
		return accountHolder;
	}
	
	//update account holder
	@Override
	public AccountHolder updateAccountHolder(AccountHolder accountHolder) throws NoResourceFoundException {
		AccountHolder accHold = accountHolderRepo.getOne(accountHolder.getId());
		accHold = accountHolder;
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
	public AccountHolder deleteAccountHolder(AccountHolder accountHolder) throws NoResourceFoundException{
		AccountHolder ach = accountHolderRepo.getOne(accountHolder.getId());
		if(ach!=null) {
			accountHolderRepo.delete(ach);
			return accountHolder;
		} else {
			throw new NoResourceFoundException("Account Does Not Exist");
		}
	}

	@Override
	public ContactDetails addContactDetails(int id, ContactDetails contactDetails) {
		AccountHolder accountHolder = accountHolderRepo.getOne(id);
		contactDetails.setAccountHolder(accountHolder);
		contactDetailsRepo.save(contactDetails);
		return contactDetails;
		
//		AccountHolder accountHolder = accountHolderRepo.getOne(id);
//		accountHolder.setContactDetails(contactDetails);
//		accountHolderRepo.save(accountHolder);
//		return contactDetails;
	}
	
}
