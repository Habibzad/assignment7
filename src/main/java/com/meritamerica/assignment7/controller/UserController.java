package com.meritamerica.assignment7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.exceptions.NoResourceFoundException;
import com.meritamerica.assignment7.models.User;
import com.meritamerica.assignment7.security.util.JwtUtil;
import com.meritamerica.assignment7.service.AccountsService;
import com.meritamerica.assignment7.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//Get all users
	//-------------
	@GetMapping("/users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	//Get user by id
	//--------------
	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User getUser(@PathVariable("id") int id) throws NoResourceFoundException {
		return userService.getUser(id);
	}
	
	//Create User
	//-------------
	@PostMapping("/createuser")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public User addUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.addUser(user);
	}
	
	//Update User
	//------------
	@PutMapping("/users/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public User updateUser(@PathVariable("id") int id, @RequestBody User user) throws NoResourceFoundException {
		if(userService.getUser(id)!=null) {
			return userService.updateUser(id, user);
		}
		throw new NoResourceFoundException("User not found");
	}

	//Delete User
	//------------
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public User deleteUsers(@PathVariable("id") int id) throws NoResourceFoundException {
		return userService.deleteUser(id);
	}

}
