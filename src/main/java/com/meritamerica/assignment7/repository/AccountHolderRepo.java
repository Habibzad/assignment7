package com.meritamerica.assignment7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.BankAccount;
@Repository
public interface AccountHolderRepo extends JpaRepository<AccountHolder, Integer> {

	void save(BankAccount ba);

}
