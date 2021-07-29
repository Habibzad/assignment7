package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.meritamerica.assignment7.enums.AccountType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "Personal_Checking_Account")
public class PersonalCheckingAccount extends BankAccount {

	// Parameterized constructor
	public PersonalCheckingAccount(double balance) {
		super(balance);
		this.interestRate = 0.05; // Interest Rate = 3%
		this.openingDate = getTime();
		this.accountType = AccountType.PERSONAL_CHECKING;
	}

	private String getTime() {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm");
		ZonedDateTime zdt = ZonedDateTime.now();
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}
}
