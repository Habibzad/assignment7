package com.meritamerica.assignment7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.models.BankAccount;
import com.meritamerica.assignment7.models.DepositTransaction;
import com.meritamerica.assignment7.models.Transaction;
import com.meritamerica.assignment7.models.TransferTransaction;
import com.meritamerica.assignment7.repository.BankAccountRepo;
import com.meritamerica.assignment7.repository.TransactionRepo;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired 
	private TransactionRepo transactionRepo;
	
	@Autowired BankAccountRepo bankAccountRepo;
	
	@Override
	public boolean transfer(int sourceAccountNumber, int targetAccountNumber, double amount) {
		BankAccount sourceAccount = bankAccountRepo.findByAccountNumber(sourceAccountNumber);
		BankAccount targetAccount = bankAccountRepo.findByAccountNumber(targetAccountNumber);
		Transaction transferTransaction = new TransferTransaction(sourceAccount, targetAccount, amount);
		transactionRepo.save(transferTransaction);
		return true;
	}
	
	@Override
	public boolean deposit(int accountNumber, double amount, String type) {
		BankAccount targetAccount = bankAccountRepo.findByAccountNumber(accountNumber);
		Transaction depositTransaction = new DepositTransaction(targetAccount, amount, type);
		transactionRepo.save(depositTransaction);
		return targetAccount.deposit(amount);
	}


}
