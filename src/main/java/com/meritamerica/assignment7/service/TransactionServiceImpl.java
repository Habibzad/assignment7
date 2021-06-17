package com.meritamerica.assignment7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.models.BankAccount;
import com.meritamerica.assignment7.models.DepositTransaction;
import com.meritamerica.assignment7.models.Transaction;
import com.meritamerica.assignment7.models.TransferTransaction;
import com.meritamerica.assignment7.models.WithdrawTransaction;
import com.meritamerica.assignment7.repository.BankAccountRepo;
import com.meritamerica.assignment7.repository.TransactionRepo;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired 
	private TransactionRepo transactionRepo;
	
	@Autowired BankAccountRepo bankAccountRepo;
	
	@Override
	public String transfer(int sourceAccountNumber, int targetAccountNumber, String description, double amount) {
		BankAccount sourceAccount = bankAccountRepo.findByAccountNumber(sourceAccountNumber);
		BankAccount targetAccount = bankAccountRepo.findByAccountNumber(targetAccountNumber);
		Transaction transferTransaction = new TransferTransaction(sourceAccount, targetAccount, description, amount);
		transactionRepo.save(transferTransaction);
		return "Transaction Successfull!";
	}
	
	@Override
	public String deposit(int accountNumber,String description, double amount) {
		BankAccount targetAccount = bankAccountRepo.findByAccountNumber(accountNumber);
		Transaction depositTransaction = new DepositTransaction(targetAccount, description, amount);
		transactionRepo.save(depositTransaction);
		return "Transaction Successfull!";
	}

	@Override
	public String withdraw(int targetAccountNum,String description, double amount) {
		BankAccount targetAccount = bankAccountRepo.findByAccountNumber(targetAccountNum);
		Transaction withdrawTransaction = new WithdrawTransaction(targetAccount,description, amount);
		transactionRepo.save(withdrawTransaction);
		return "Transaction Successfull!";
	}


}
