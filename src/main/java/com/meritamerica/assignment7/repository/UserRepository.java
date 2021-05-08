package com.meritamerica.assignment7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}