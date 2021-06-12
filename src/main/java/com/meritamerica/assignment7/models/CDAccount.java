package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class CDAccount extends BankAccount {
//	Instance variables
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cdoffering_id")

	private CDOffering cdOffering;

//	Parameterized Constructor	
	public CDAccount(double balance, CDOffering offering) {
		super(balance);
		this.cdOffering = offering;
		this.openingDate = getTime();
	}

//	Getters and Setters
	public CDOffering getCdOffering() {
		return cdOffering;
	}

	public void setCdOffering(CDOffering cdOffering) {
		this.cdOffering = cdOffering;
	}
	
	private String getTime() {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm");
		ZonedDateTime zdt = ZonedDateTime.now();
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}
}
