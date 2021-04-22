package com.meritamerica.assignment6.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CDAccount extends BankAccount {
//	Instance variables
	private CDOffering cdOffering;

	@Id
	private int id;
	
// 	Default Constructor
	public CDAccount() {
	}

//	Parameterized Constructor	
	public CDAccount(double balance, CDOffering offering) {
		super(balance);
		this.cdOffering = offering;
	}

//	Getters and Setters
	public CDOffering getCdOffering() {
		return cdOffering;
	}

	public void setCdOffering(CDOffering cdOffering) {
		this.cdOffering = cdOffering;
	}
}
