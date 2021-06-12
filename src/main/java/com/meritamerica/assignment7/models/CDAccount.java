package com.meritamerica.assignment7.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CDAccount extends BankAccount {
//	Instance variables
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cdoffering_id")

	private CDOffering cdOffering;

	public CDAccount() {
	}
	
	private String getTime() {
		//Create formatter
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm");
		 
		//Zoned datetime instance
		ZonedDateTime zdt = ZonedDateTime.now();
		 
		//Get formatted String
		String zdtString = FOMATTER.format(zdt);
		return zdtString;
	}

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
}
