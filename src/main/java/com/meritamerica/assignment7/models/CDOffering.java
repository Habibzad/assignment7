package com.meritamerica.assignment7.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "CD_Offering")
public class CDOffering {
	//	Static Variable
	private static int nextID = 1;
	//	Instance Variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double interestRate;
	private int term;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cdOffering")
	@JsonIgnore
	private List<CDAccount> cdAccounts;

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
}
