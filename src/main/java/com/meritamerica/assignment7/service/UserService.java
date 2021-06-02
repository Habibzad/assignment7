package com.meritamerica.assignment7.service;

import java.util.List;

import com.meritamerica.assignment7.models.User;

public interface UserService {
	public User addUser(User user);
	public User getUser(int id);
	public User getUserByUserName(String username);
	public List<User> getUsers();
	public User updateUser(User user);
	public User deleteUser(User user);
}
