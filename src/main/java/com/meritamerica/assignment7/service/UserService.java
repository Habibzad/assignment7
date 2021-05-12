package com.meritamerica.assignment7.service;

import com.meritamerica.assignment7.models.User;

public interface UserService {
	public User addUser(User user);
	public User getUser(int id);
	public User getUserByUserName(String username);
}
