package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;

@Entity
public class DepositTransaction extends Transaction {

	public DepositTransaction(BankAccount targetAccount, double amount, String transactionType) {
		super();
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transactionDate = getTime();
		this.transactionType = transactionType;
		this.targetAccount.deposit(amount);
	}

	private String getTime() {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");
		ZonedDateTime zdt = ZonedDateTime.now();
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}

}
