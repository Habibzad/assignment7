package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;

@Entity
public class WithdrawTransaction extends Transaction {

	WithdrawTransaction(BankAccount targetAccount, double amount, String transactionType) {
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transactionDate = getTime();
		this.transactionType = transactionType;
		this.targetAccount.withdraw(amount);
	}

	private String getTime() {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");
		ZonedDateTime zdt = ZonedDateTime.now();
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}
}
