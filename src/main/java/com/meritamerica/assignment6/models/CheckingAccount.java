package com.meritamerica.assignment6.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CheckingAccount extends BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	Default constructor	
	public CheckingAccount() {
	}

//	Parameterized constructor		
	public CheckingAccount(double balance) {
		super(balance);
	}
}
