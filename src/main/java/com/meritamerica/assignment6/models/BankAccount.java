package com.meritamerica.assignment6.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankAccount {
	
	@Id
//	@Column(name = "id")
	private int id;
	
//	Account Number Generator
	private static long nextAccountNumber = 1;

//	Instance Variables	
	private long accountNumber;
	private double balance;
	private double interestRate;
	private LocalDateTime openingDate;

//	Default Constructor
	public BankAccount() {
	}

//	Parameterized Constructor
	public BankAccount(double balance) {
		this.accountNumber = nextAccountNumber++;
		this.balance = balance;
		this.interestRate = 0.01; // Interest Rate = 1%
		this.openingDate = LocalDateTime.now();
	}

//	Getters and Setters
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public LocalDateTime getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDateTime openingDate) {
		this.openingDate = openingDate;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", balance=" + balance + ", interestRate=" + interestRate
				+ ", openingDate=" + openingDate + "]";
	}

// Account methods
	/**
	 * This method withdraws from the account if the withdraw amount is less than
	 * balance
	 * 
	 * @param amount
	 * @return boolean
	 */
	public boolean withdraw(double amount) {
		if (amount <= this.balance) {
			this.balance -= amount;
			return true;
		} else {
			return false;
		}
	}

// 	Deposit Method
	/**
	 * This method deposits in the account if the deposit amount is greater than
	 * zero
	 * 
	 * @param amount
	 * @return boolean
	 */
	public boolean deposit(double amount) {
		if (amount <= 0) {
			return false;
		} else {
			this.balance += amount;
			return true;
		}
	}

	/**
	 * This method returns future value of the account
	 * 
	 * @param years
	 * @return double
	 */
	public double futureValue(int years) {
		return getBalance() * (Math.pow(1 + getInterestRate(), years));
	}
}
