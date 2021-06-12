package com.meritamerica.assignment7.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SavingsAccount extends BankAccount {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;

// 	Default Constructor
	public SavingsAccount() {
	}

//	Parameterized constructor
	public SavingsAccount(double balance) {
		super(balance);
	}
}
