package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;

@Entity
public class TransferTransaction extends Transaction {

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
	
	private String getTime() {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");
		ZonedDateTime zdt = ZonedDateTime.now();
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}
}
