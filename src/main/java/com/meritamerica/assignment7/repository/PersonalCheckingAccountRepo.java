package com.meritamerica.assignment7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment7.models.PersonalCheckingAccount;

@Repository
public interface PersonalCheckingAccountRepo extends JpaRepository<PersonalCheckingAccount, Integer> {

}
	