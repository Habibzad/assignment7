package com.meritamerica.assignment7.models;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BankAccount {

//	Account Number Generator
//	private static long nextAccountNumber = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accountNumber;
	
	private double balance;
	private double interestRate;
	
	protected String openingDate;

	@ManyToOne
	@JoinColumn(name = "account_holder_id")
	@JsonIgnore
	private AccountHolder accountHolder;

//	Default Constructor
	public BankAccount() {
	}
	
	private String getTime() {
		//Create formatter
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");
		 
		//Zoned datetime instance
		ZonedDateTime zdt = ZonedDateTime.now();
		 
		//Get formatted String
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}

//	Parameterized Constructor
	public BankAccount(double balance) {
//		this.accountNumber = nextAccountNumber++;
		this.balance = balance;
		this.interestRate = 0.01; // Interest Rate = 1%
		this.openingDate = getTime();
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

	public String getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}

	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
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
