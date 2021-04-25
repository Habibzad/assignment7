package com.meritamerica.assignment6.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AccountHoldersContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contact_id;
	private String phone;
	private String email;
	private String address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_id", referencedColumnName = "id")
	private AccountHolder accountHolder;
	
	public AccountHoldersContactDetails() {}
	
	public int getContact_id() {
		return contact_id;
	}
	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
