package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SavingsAccount extends BankAccount {

	private String getTime() {
		//Create formatter
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm");
		 
		//Zoned datetime instance
		ZonedDateTime zdt = ZonedDateTime.now();
		 
		//Get formatted String
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}
	
// 	Default Constructor
	public SavingsAccount() {
	}

//	Parameterized constructor
	public SavingsAccount(double balance) {
		super(balance);
		this.openingDate = getTime();
	}
}
