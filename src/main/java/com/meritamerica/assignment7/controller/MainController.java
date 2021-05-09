package com.meritamerica.assignment7.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@GetMapping("/")
	public String Home(){
		return "Welcome";
	}
	
//	@PostMapping("/authenticate")
//	public String authenticate(){
//		return "authenticate";
//	}

}
