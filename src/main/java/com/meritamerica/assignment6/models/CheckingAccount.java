package com.meritamerica.assignment6.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CheckingAccount extends BankAccount {
	@Id
	private int id;
	
//	Default constructor	
	public CheckingAccount() {
	}

//	Parameterized constructor		
	public CheckingAccount(double balance) {
		super(balance);
	}
}
