package com.meritamerica.assignment7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
	private int sourceAccountNum;
	private int targetAccountNum;
	private double amount;
	private String transactionType;
	
}
