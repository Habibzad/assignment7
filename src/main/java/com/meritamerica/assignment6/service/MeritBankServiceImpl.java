package com.meritamerica.assignment6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment6.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment6.models.AccountHolder;
import com.meritamerica.assignment6.models.CDAccount;
import com.meritamerica.assignment6.models.CDOffering;
import com.meritamerica.assignment6.models.CheckingAccount;
import com.meritamerica.assignment6.models.SavingsAccount;
import com.meritamerica.assignment6.repository.AccountHolderRepo;
import com.meritamerica.assignment6.repository.CDOfferingRepo;
import com.meritamerica.assignment6.repository.CheckingAccountRepo;

@Service
public class MeritBankServiceImpl implements MeritBankService {
	@Autowired
	private CheckingAccountRepo checkingAccountRepo;
	@Autowired
	private CDOfferingRepo cdOfferingRepo;
	@Autowired
	private AccountHolderRepo accountHolderRepo;

//	Constructor
	public MeritBankServiceImpl() {
	}

	@Override
	public List<CDOffering> getCDOfferings() {
		return cdOfferingRepo.findAll();
	}

	@Override
	public CDOffering getCDOffering(int id) {
		return cdOfferingRepo.getOne(id);
	}

	@Override
	public CDOffering addCDOffering(CDOffering cdOffering) {
		return cdOfferingRepo.save(cdOffering);
	}

	@Override
	public void clearCDOfferings() {
		cdOfferingRepo.deleteAll();
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
	public AccountHolder addAccountHolder(AccountHolder accountHolder) {
		return accountHolderRepo.save(accountHolder);
	}

	@Override
	public CheckingAccount addCheckingAccount(int id, CheckingAccount checkingAccount) {
		AccountHolder accountHolder = accountHolderRepo.getOne(id);
		CheckingAccount checkAcc = new CheckingAccount(checkingAccount.getBalance());
		checkAcc.setAccountHolder(accountHolder);
		return checkingAccountRepo.save(checkAcc);
	}

	@Override
	public SavingsAccount addSavingsAccount(int id, SavingsAccount savingsAccount) {
		AccountHolder accountHolder = accountHolderRepo.getOne(id);
		SavingsAccount savAcc = new SavingsAccount(savingsAccount.getBalance());
		try {
			accountHolder.addSavingsAccount(savAcc);
			accountHolderRepo.save(accountHolder);
		} catch (ExceedsCombinedBalanceLimitException e) {
			e.printStackTrace();
		}
		return savingsAccount;
	}

	@Override
	public CDAccount addCDAccount(int id, CDAccount cdAccount) {
		AccountHolder accHolder = accountHolderRepo.getOne(id);
		CDAccount cdAcc = new CDAccount(cdAccount.getBalance(), cdAccount.getCdOffering());
		try {
			accHolder.addCdAccount(cdAcc);
			accountHolderRepo.save(accHolder);
		} catch (ExceedsCombinedBalanceLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cdAccount;
	}

}
