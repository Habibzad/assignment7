package com.meritamerica.assignment7.models;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;

@Entity
public class DepositTransaction extends Transaction {
	
	private String getTime() {
		//Create formatter
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");
		 
		//Zoned datetime instance
		ZonedDateTime zdt = ZonedDateTime.now();
		 
		//Get formatted String
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}
	public DepositTransaction(BankAccount targetAccount, double amount, String transactionType) {
		super();
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transactionDate = getTime();
		this.transactionType = transactionType;
		this.targetAccount.deposit(amount);
	}

}
