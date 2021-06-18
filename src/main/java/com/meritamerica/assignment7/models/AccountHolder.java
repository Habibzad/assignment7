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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	private String phone;
	private String email;
	private String address;
	@OneToMany(mappedBy = "accountHolder", cascade = CascadeType.ALL)
	private List<PersonalCheckingAccount> personalCheckingAccount = new ArrayList<>();

	@OneToMany(mappedBy = "accountHolder", cascade = CascadeType.ALL)
	private List<DBACheckingAccount> dbaCheckingAccounts = new ArrayList<>();

	@OneToMany(mappedBy = "accountHolder", cascade = CascadeType.ALL)
	private List<SavingsAccount> savingsAccounts = new ArrayList<>();

	@OneToMany(mappedBy = "accountHolder", cascade = CascadeType.ALL)
	private List<RegularIRA> regularIRA = new ArrayList<>();

	@OneToMany(mappedBy = "accountHolder", cascade = CascadeType.ALL)
	private List<RothIRA> rothIRA = new ArrayList<>();

	@OneToMany(mappedBy = "accountHolder", cascade = CascadeType.ALL)
	private List<RolloverIRA> rolloverIRA = new ArrayList<>();

	@OneToMany(mappedBy = "accountHolder", cascade = CascadeType.ALL)
	private List<CDAccount> cdAccounts = new ArrayList<>();

	private double combinedBalance;

//	@OneToOne(mappedBy = "accountHolder")
//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	private ContactDetails contactDetails;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User user;

//	Parameterized Constructor
	public AccountHolder(
			String firstName, 
			String middleName, 
			String lastName, 
			String ssn,
			String phone,
			String email,
			String address) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public double getPersonalCheckingBalance() {
		double total = 0;
		for (int i = 0; i < personalCheckingAccount.size(); i++) {
			total += personalCheckingAccount.get(i).getBalance();
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
	
	public double getDbaCheckingBalance() {
		double total = 0;
		for (int i = 0; i < dbaCheckingAccounts.size(); i++) {
			total += dbaCheckingAccounts.get(i).getBalance();
		}
		return total;
	}
	
	public double getRegularIRABalance() {
		double total = 0;
		for (int i = 0; i < regularIRA.size(); i++) {
			total += regularIRA.get(i).getBalance();
		}
		return total;
	}
	
	public double getRolloverIRABalance() {
		double total = 0;
		for (int i = 0; i < rolloverIRA.size(); i++) {
			total += rolloverIRA.get(i).getBalance();
		}
		return total;
	}
	
	public double getRothIRABalance() {
		double total = 0;
		for (int i = 0; i < rothIRA.size(); i++) {
			total += rothIRA.get(i).getBalance();
		}
		return total;
	}

//	Get All Accounts Combined Balance

	public double getCombinedBalance() {
		combinedBalance = 
				getPersonalCheckingBalance() + 
				getSavingsBalance() + 
				getCDBalance() +
				getDbaCheckingBalance() +
				getRegularIRABalance() +
				getRolloverIRABalance()+
				getRothIRABalance();
		return combinedBalance;
	}

}
