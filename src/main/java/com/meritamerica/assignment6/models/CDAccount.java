package com.meritamerica.assignment6.models;

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
