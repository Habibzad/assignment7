package com.meritamerica.assignment7.service;

import java.util.List;

import com.meritamerica.assignment7.exceptions.NoResourceFoundException;
import com.meritamerica.assignment7.models.User;

public interface UserService {
	public User addUser(User user);
	public User getUser(int id)throws NoResourceFoundException;
	public User getUserByUserName(String username)throws NoResourceFoundException;
	public List<User> getUsers();
	public User updateUser(int id, User user)throws NoResourceFoundException;
	public User deleteUser(int id) throws NoResourceFoundException;
}
