package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;

import com.meritamerica.assignment7.enums.TransactionType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class DepositTransaction extends Transaction {

	public DepositTransaction(BankAccount targetAccount, String description, double amount) {
		super();
		this.targetAccount = targetAccount;
		this.description = description;
		this.amount = amount;
		this.transactionDate = getTime();
		this.transactionType = TransactionType.DEPOSIT;
		this.targetAccount.deposit(amount);
	}

	private String getTime() {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");
		ZonedDateTime zdt = ZonedDateTime.now();
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}

}
