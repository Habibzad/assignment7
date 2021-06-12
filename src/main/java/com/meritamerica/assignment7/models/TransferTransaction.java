package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;

@Entity
public class TransferTransaction extends Transaction {
	private String getTime() {
		//Create formatter
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");
		 
		//Zoned datetime instance
		ZonedDateTime zdt = ZonedDateTime.now();
		 
		//Get formatted String
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}

	public TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		super();
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transactionDate = getTime();
		this.transactionType = "Transfer";
		this.sourceAccount.withdraw(amount);
		this.targetAccount.deposit(amount);
	}
}
