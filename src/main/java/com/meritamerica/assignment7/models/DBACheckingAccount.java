package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.meritamerica.assignment7.enums.AccountType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "DBA_Checking_Account")
public class DBACheckingAccount extends BankAccount{
//	Parameterized constructor
	public DBACheckingAccount(double balance) {
		super(balance);
		this.interestRate = 0.004; // Interest Rate = 3%
		this.openingDate = getTime();
		this.accountType = AccountType.DBA_CHECKING;
	}
	
	private String getTime() {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm");
		ZonedDateTime zdt = ZonedDateTime.now();
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}
}
