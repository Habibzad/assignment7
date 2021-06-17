package com.meritamerica.assignment7.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.meritamerica.assignment7.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	protected String transactionDate;
	protected String description;
	protected TransactionType transactionType;
	protected double amount;
	
	@ManyToOne
	@JoinColumn(name = "source_account_id")
	BankAccount sourceAccount;
	
	@ManyToOne
	@JoinColumn(name = "target_account_id")
	BankAccount targetAccount;

}
