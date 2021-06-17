package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritamerica.assignment7.enums.TransactionType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class TransferTransaction extends Transaction {

	public TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, String description, double amount) {
		super();
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.description = description;
		this.amount = amount;
		this.transactionDate = getTime();
		this.transactionType = TransactionType.TRANSFER;
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
