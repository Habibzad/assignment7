package com.meritamerica.assignment7.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.models.User;
import com.meritamerica.assignment7.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/authenticate/createuser")
	@ResponseStatus(HttpStatus.CREATED)
	public User addUser(@RequestBody User user) {
//		User newUser = new User(user.getUserName(), user.getPassword());
		return userService.addUser(user);
	}

}
