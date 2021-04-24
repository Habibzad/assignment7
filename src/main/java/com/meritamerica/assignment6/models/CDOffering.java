package com.meritamerica.assignment6.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CDOffering {
//	Static Variable
	private static int nextID = 1;
//	Instance Variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double interestRate;
	private int term;

// 	Default Constructor
	public CDOffering() {
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cdOffering")
	private List<CDAccount> cdAccounts;

	public List<CDAccount> getCdAccounts() {
		return cdAccounts;
	}

	public void setCdAccounts(List<CDAccount> cdAccounts) {
		this.cdAccounts = cdAccounts;
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param term
	 * @param interestRate
	 */
	public CDOffering(int term, double interestRate) {
		this.id = nextID++;
		this.term = term;
		this.interestRate = interestRate;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		return "CDOfferings [id=" + id + ", term=" + term + ", interestRate=" + interestRate + "]";
	}
}
