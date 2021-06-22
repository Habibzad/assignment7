package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.meritamerica.assignment7.enums.AccountType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class SavingsAccount extends BankAccount {

//	Parameterized constructor
	public SavingsAccount(double balance) {
		super(balance);
		this.interestRate = 0.55; // Interest Rate = 3%
		this.openingDate = getTime();
		this.accountType = AccountType.SAVINGS;
	}
	
	private String getTime() {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm");
		ZonedDateTime zdt = ZonedDateTime.now();
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}
}
