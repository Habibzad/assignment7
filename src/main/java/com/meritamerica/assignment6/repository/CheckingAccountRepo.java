package com.meritamerica.assignment6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment6.models.CheckingAccount;

@Repository
public interface CheckingAccountRepo extends JpaRepository<CheckingAccount, Integer> {

}
