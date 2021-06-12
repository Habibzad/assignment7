package com.meritamerica.assignment7.service;

public interface TransactionService {
	public boolean deposit(int accountNumber, double amount, String type);
	public boolean transfer(int sourceAccountNumber, int targetAccountNumber, double amount);
}
