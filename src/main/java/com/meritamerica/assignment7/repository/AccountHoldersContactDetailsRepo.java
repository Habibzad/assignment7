package com.meritamerica.assignment7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment7.models.AccountHoldersContactDetails;

@Repository
public interface AccountHoldersContactDetailsRepo extends JpaRepository<AccountHoldersContactDetails, Integer> {

}
