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
import com.meritamerica.assignment7.exceptions.InvalidArgumentException;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AccountHolder() {

	}

//	Parameterized Constructor
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
	}

//	Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<CheckingAccount> getCheckingAccounts() {
		return checkingAccounts;
	}

	public void setCheckingAccounts(List<CheckingAccount> checkingAccounts) {
		this.checkingAccounts = checkingAccounts;
	}

	public List<SavingsAccount> getSavingsAccounts() {
		return savingsAccounts;
	}

	public void setSavingsAccounts(List<SavingsAccount> savingsAccounts) {
		this.savingsAccounts = savingsAccounts;
	}

	public List<CDAccount> getCdAccounts() {
		return cdAccounts;
	}

	public void setCdAccounts(List<CDAccount> cdAccounts) {
		this.cdAccounts = cdAccounts;
	}

//	Get Number of Accounts

	public int getNumberOfCheckingAccounts() {
		return this.checkingAccounts.size();
	}

	public int getNumberOfSavingsAccounts() {
		return this.savingsAccounts.size();
	}

	public int getNumberOfCDAccounts() {
		return this.cdAccounts.size();
	}

//	Get individual accounts combined balance

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

	public AccountHoldersContactDetails getAccountHoldersContactDetails() {
		return accountHoldersContactDetails;
	}

	public void setAccountHoldersContactDetails(AccountHoldersContactDetails accountHoldersContactDetails) {
		this.accountHoldersContactDetails = accountHoldersContactDetails;
	}

//	Get All Accounts Combined Balance

	public double getCombinedBalance() {
		combinedBalance = getCheckingBalance() + getSavingsBalance() + getCDBalance();
		return combinedBalance;
	}

//	ToString Method
	@Override
	public String toString() {
		return lastName + "," + middleName + "," + firstName + "," + ssn;
	}

}
