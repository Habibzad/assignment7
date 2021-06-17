package com.meritamerica.assignment7.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String password;
	private boolean active;
	private String roles;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private AccountHolder accountHolder;

	public User(String userName, String password, boolean active, String role) {
		this.userName = userName;
		this.password = password;
		this.active = active;
		this.roles = role;
	}
}
