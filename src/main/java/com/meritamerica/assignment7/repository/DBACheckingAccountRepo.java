package com.meritamerica.assignment7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.DBACheckingAccount;

public interface DBACheckingAccountRepo extends JpaRepository<DBACheckingAccount, Integer> {

}
