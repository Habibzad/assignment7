package com.meritamerica.assignment7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.enums.AccountStatus;
import com.meritamerica.assignment7.enums.AccountType;
import com.meritamerica.assignment7.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment7.exceptions.InvalidArgumentException;
import com.meritamerica.assignment7.exceptions.NoResourceFoundException;
import com.meritamerica.assignment7.exceptions.NoSuchAccountException;
import com.meritamerica.assignment7.exceptions.ReachedAccountLimitException;
import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.BankAccount;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.CDOffering;
import com.meritamerica.assignment7.models.DBACheckingAccount;
import com.meritamerica.assignment7.models.PersonalCheckingAccount;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.repository.AccountHolderRepo;
import com.meritamerica.assignment7.repository.BankAccountRepo;
import com.meritamerica.assignment7.repository.CDAccountRepo;
import com.meritamerica.assignment7.repository.CDOfferingRepo;
import com.meritamerica.assignment7.repository.DBACheckingAccountRepo;
import com.meritamerica.assignment7.repository.PersonalCheckingAccountRepo;
import com.meritamerica.assignment7.repository.SavingsAccountRepo;

@Service
public class AccountsServiceImpl implements AccountsService {
	@Autowired
	private AccountHolderRepo accountHolderRepo;
	@Autowired
	private PersonalCheckingAccountRepo checkingAccountRepo;
	@Autowired
	private SavingsAccountRepo savingsAccountRepo;
	@Autowired
	private CDAccountRepo cdAccountRepo;
	@Autowired
	private CDOfferingRepo cdOfferingRepo;

	@Autowired
	private DBACheckingAccountRepo dbaCheckingAccountRepo;
	
	@Autowired
	private BankAccountRepo bankAccountRepo;

//	SavingsAccount Methods
	@Override
	public SavingsAccount addSavingsAccount(int id, SavingsAccount savingsAccount)
			throws ExceedsCombinedBalanceLimitException, InvalidArgumentException, ReachedAccountLimitException,
			NoResourceFoundException {
		if (accountHolderRepo.existsById(id)) {
			AccountHolder accountHolder = accountHolderRepo.getOne(id);
			if (accountHolder.getCombinedBalance() + savingsAccount.getBalance() > 250000) {
				throw new ExceedsCombinedBalanceLimitException("Combined balance cannot be greater than 250000");
			}
			if (savingsAccount.getBalance() < 0) {
				throw new InvalidArgumentException("Balance cannt be negative");
			}
			if (accountHolder.getSavingsAccounts().size() == 1) {
				throw new ReachedAccountLimitException("Each account holder can have only one savings account");
			}
			SavingsAccount savAcc = new SavingsAccount(savingsAccount.getBalance());
			savAcc.setAccountHolder(accountHolder);
			return savingsAccountRepo.save(savAcc);
		}
		throw new NoResourceFoundException("Account holder not found found");
	}

//	CheckingAccount Methods
	@Override
	public PersonalCheckingAccount addPersonalCheckingAccount(int id, PersonalCheckingAccount checkingAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException, InvalidArgumentException,
			ReachedAccountLimitException {
		if (accountHolderRepo.existsById(id)) {
			AccountHolder accountHolder = accountHolderRepo.getOne(id);
			if (accountHolder.getCombinedBalance() + checkingAccount.getBalance() > 250000) {
				throw new ExceedsCombinedBalanceLimitException("Combined balance cannot be greater than 250000");
			}
			if (checkingAccount.getBalance() < 0) {
				throw new InvalidArgumentException("Initial balance cant be negative");
			}
			if (accountHolder.getPersonalCheckingAccount().size() == 1) {
				throw new ReachedAccountLimitException(
						"Each account holder can have only one personal checking account");
			}
			PersonalCheckingAccount checkAcc = new PersonalCheckingAccount(checkingAccount.getBalance());
			checkAcc.setAccountHolder(accountHolder);
			return checkingAccountRepo.save(checkAcc);
		}
		throw new NoSuchAccountException("No such account found");
	}

	@Override
	public DBACheckingAccount addDBACheckingAccount(int id, DBACheckingAccount dbaCheckingAccount)
			throws NoResourceFoundException, ExceedsCombinedBalanceLimitException, InvalidArgumentException,
			ReachedAccountLimitException, NoSuchAccountException {
		if (accountHolderRepo.existsById(id)) {
			AccountHolder accountHolder = accountHolderRepo.getOne(id);
			if (accountHolder.getCombinedBalance() + dbaCheckingAccount.getBalance() > 250000) {
				throw new ExceedsCombinedBalanceLimitException("Combined balance cannot be greater than 250000");
			}
			if (dbaCheckingAccount.getBalance() < 0) {
				throw new InvalidArgumentException("Initial balance cant be negative");
			}
			if (accountHolder.getDbaCheckingAccounts().size() == 3) {
				throw new ReachedAccountLimitException(
						"Each account holder cannot have more than three DBA checking accounts");
			}
			DBACheckingAccount dbaCheckAcc = new DBACheckingAccount(dbaCheckingAccount.getBalance());
			dbaCheckAcc.setAccountHolder(accountHolder);
			return dbaCheckingAccountRepo.save(dbaCheckAcc);
		}
		throw new NoSuchAccountException("No such account found");

	}

//	CDAccount Methods
	@Override
	public CDAccount addCDAccount(int id, CDAccount cdAccount)
			throws ExceedsCombinedBalanceLimitException, NoResourceFoundException, InvalidArgumentException {
		if (accountHolderRepo.existsById(id)) {
			AccountHolder accountHolder = accountHolderRepo.getOne(id);
			if (accountHolder.getCombinedBalance() + cdAccount.getBalance() > 250000) {
				throw new ExceedsCombinedBalanceLimitException("Combined balance cannot be greater than 250000");
			}
			if (cdAccount.getBalance() < 0) {
				throw new InvalidArgumentException("Initial balance cant be negative");
			}
			CDOffering offering = cdOfferingRepo.getOne(cdAccount.getCdOffering().getId());
			CDAccount cdAcc = new CDAccount(cdAccount.getBalance(), offering);
			cdAcc.setAccountHolder(accountHolder);
			return cdAccountRepo.save(cdAcc);
		}
		throw new NoResourceFoundException("Account holder not found");
	}

	@Override
	public boolean closeAccount(int accountNumber) throws InvalidArgumentException {
		BankAccount ba = bankAccountRepo.findByAccountNumber(accountNumber);
		if (ba.getAccountType() == AccountType.CD) {
			if (ba.getBalance() > 0) {
				throw new InvalidArgumentException("Account balance should be 0 before closing");
			}
		}
		ba.setStatus(AccountStatus.CLOSED);
		bankAccountRepo.save(ba);
		return true;
	}

}
