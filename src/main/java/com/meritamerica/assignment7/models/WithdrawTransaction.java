package com.meritamerica.assignment7.models;

import javax.persistence.Entity;

@Entity
public class WithdrawTransaction extends Transaction {
	
	WithdrawTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.targetAccount.withdraw(amount);
	}
}
