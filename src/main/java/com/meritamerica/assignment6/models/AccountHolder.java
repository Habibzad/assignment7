package com.meritamerica.assignment6.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.meritamerica.assignment6.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment6.exceptions.InvalidArgumentException;

@Entity
public class AccountHolder {
// 	Constants and static variables
	public static final long BALANCE_LIMIT = 250000;
	private static int nextID = 1;

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
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "contact_id")
    private AccountHoldersContactDetails accountHoldersContactDetails;

	public AccountHolder() {

	}

//	Parameterized Constructor
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.id = nextID++;
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

	public void setFirstName(String firstName) throws InvalidArgumentException{
		if(firstName=="") {
			throw new InvalidArgumentException("First Name Cannot be blank");
		}
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

	public void setLastName(String lastName) throws InvalidArgumentException{
		if(lastName=="") {
			throw new InvalidArgumentException("Last Name Cannot be blank");
		}
		this.lastName = lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) throws InvalidArgumentException{
		if(ssn=="") {
			throw new InvalidArgumentException("SSN Name Cannot be blank");
		}
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

//	Add Account Methods	
	/**
	 * Add Checking Account Method
	 * 
	 * @param checkingAccount
	 * @throws ExceedsCombinedBalanceLimitException
	 */

	public void addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException {

		if (getCombinedBalance() + checkingAccount.getBalance() >= BALANCE_LIMIT) {

			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another account.");

		} else {
			this.checkingAccounts.add(checkingAccount);
		}
	}

	public CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
		CheckingAccount newCheckingAccount = new CheckingAccount(openingBalance);

		if (getCombinedBalance() + openingBalance >= BALANCE_LIMIT) {
			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another.");
		} else {
			this.checkingAccounts.add(newCheckingAccount);
			return newCheckingAccount;
		}

	}

	/**
	 * Add Saving Account Method
	 * 
	 * @param savingsAccount
	 * @throws ExceedsCombinedBalanceLimitException
	 */

	public void addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException {

		if (getCombinedBalance() + savingsAccount.getBalance() >= BALANCE_LIMIT) {

			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another account.");

		} else {
			this.savingsAccounts.add(savingsAccount);
		}

	}

	public SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
		SavingsAccount newSavingsAccount = new SavingsAccount(openingBalance);
		if (getCombinedBalance() + openingBalance >= BALANCE_LIMIT) {

			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another.");

		} else {
			this.savingsAccounts.add(newSavingsAccount);
			return newSavingsAccount;
		}
	}

	/**
	 * Add CD Account Method
	 * 
	 * @param cdAccount
	 * @throws ExceedsCombinedBalanceLimitException
	 */

	public void addCdAccount(CDAccount cdAccount) throws ExceedsCombinedBalanceLimitException {

		if (getCombinedBalance() + cdAccount.getBalance() >= BALANCE_LIMIT) {

			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another account.");

		} else {
			this.cdAccounts.add(cdAccount);
		}

	}

	public void addCdAccounts(double openingBalance, CDOffering offering) throws ExceedsCombinedBalanceLimitException {
		CDAccount newCDAccount = new CDAccount(openingBalance, offering);

		if (getCombinedBalance() + newCDAccount.getBalance() >= BALANCE_LIMIT) {

			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another account.");

		} else {
			this.cdAccounts.add(newCDAccount);
		}

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
