package com.meritamerica.assignment6.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SavingsAccount extends BankAccount {
	@Id
	private int id;
	
// 	Default Constructor
	public SavingsAccount() {
	} 
	
//	Parameterized constructor
	public SavingsAccount(double balance) {
		super(balance);
	}
}
