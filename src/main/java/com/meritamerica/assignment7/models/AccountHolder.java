package com.meritamerica.assignment7.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountHolder {
// 	Constants and static variables
	public static final long BALANCE_LIMIT = 250000;

//	Instance Variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "First Name cannot be blank")
	@NotNull(message = "First name cannot be blank")
	private String firstName;
	
	private String middleName;
	
	@NotBlank(message = "Last Name cannot be blank")
	@NotNull(message = "Last name cannot be blank")
	private String lastName;
	
	@NotBlank(message = "SSN cannot be blank")
	@NotNull(message = "SSN cannot be blank")
	private String ssn;

	@OneToMany(mappedBy = "accountHolder")
	private List<CheckingAccount> checkingAccounts = new ArrayList<>();
	
	@OneToMany(mappedBy = "accountHolder")
	private List<SavingsAccount> savingsAccounts = new ArrayList<>();
	
	@OneToMany(mappedBy = "accountHolder")
	private List<CDAccount> cdAccounts = new ArrayList<>();

	private double combinedBalance;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private AccountHoldersContactDetails accountHoldersContactDetails;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;

	public double getCheckingBalance() {
		double total = 0;
		for (int i = 0; i < checkingAccounts.size(); i++) {
			total += checkingAccounts.get(i).getBalance();
		}
		return total;
	}

	public double getSavingsBalance() {
		double total = 0;
		for (int i = 0; i < savingsAccounts.size(); i++) {
			total += savingsAccounts.get(i).getBalance();
		}
		return total;
	}

	public double getCDBalance() {
		double total = 0;
		for (int i = 0; i < cdAccounts.size(); i++) {
			total += cdAccounts.get(i).getBalance();
		}
		return total;
	}

	public double getCombinedBalance() {
		combinedBalance = getCheckingBalance() + getSavingsBalance() + getCDBalance();
		return combinedBalance;
	}

}
