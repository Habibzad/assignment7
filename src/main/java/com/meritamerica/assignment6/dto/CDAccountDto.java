package com.meritamerica.assignment6.dto;

import com.meritamerica.assignment6.models.CDOffering;

public class CDAccountDto {
	private double balance;
	private CDOffering cdOffering;
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public CDOffering getCdOffering() {
		return cdOffering;
	}
	public void setCdOffering(CDOffering cdOffering) {
		this.cdOffering = cdOffering;
	}
	
	
}
