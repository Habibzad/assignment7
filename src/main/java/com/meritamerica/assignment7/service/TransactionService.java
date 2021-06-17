package com.meritamerica.assignment7.service;

public interface TransactionService {
	public String deposit(int accountNumber,String description, double amount);
	public String transfer(int sourceAccountNumber, int targetAccountNumber,String description, double amount);
	public String withdraw(int targetAccountNum,String description, double amount);
}
