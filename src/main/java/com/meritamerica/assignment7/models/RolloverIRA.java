package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.meritamerica.assignment7.enums.AccountType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "Rollover_IRA_Account")
public class RolloverIRA extends BankAccount{
//	Parameterized constructor
	public RolloverIRA(double balance) {
		super(balance);
		this.openingDate = getTime();
		this.accountType = AccountType.ROLLOVER_IRA;
	}
	
	private String getTime() {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm");
		ZonedDateTime zdt = ZonedDateTime.now();
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}
}
