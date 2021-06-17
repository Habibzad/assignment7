package com.meritamerica.assignment7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.BankAccount;

public interface BankAccountRepo extends JpaRepository<BankAccount, Integer>{
	BankAccount findByAccountNumber(long accountNumber);
}
