package com.meritamerica.assignment7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.exceptions.NoResourceFoundException;
import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.User;
import com.meritamerica.assignment7.repository.AccountHolderRepo;
import com.meritamerica.assignment7.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Autowired
	private AccountHolderRepo accountHolderRepo;

	@Override
	public User getUser(int id) {
		return userRepository.getOne(id);
	}

	@Override
	public User getUserByUserName(String username) {
		return userRepository.findByUserName(username).orElse(null);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		User oldUser = userRepository.getOne(user.getId());
		oldUser = user;
		return userRepository.save(oldUser);
	}

	@Override
	public User deleteUser(User user) throws NoResourceFoundException {

		User u = userRepository.getOne(user.getId());

		if (u != null) {
			if (u.getAccountHolder() != null) {
				AccountHolder accHolder = u.getAccountHolder();
				accHolder.setUser(null);
				accountHolderRepo.save(accHolder);
				userRepository.delete(u);
				return u;
			}
			userRepository.delete(u);
			return u;
		}
		throw new NoResourceFoundException("Account Does Not Exist");
	}

}
