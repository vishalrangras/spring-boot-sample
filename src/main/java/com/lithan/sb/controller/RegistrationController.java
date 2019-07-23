package com.lithan.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lithan.sb.dto.RegistrationFormDto;
import com.lithan.sb.model.User;
import com.lithan.sb.service.RegistrationService;

@RestController
@RequestMapping(value = "/user")
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	@PostMapping("/save")
	public String registerUser(@RequestBody RegistrationFormDto registrationFormDto) {
		try{
			User user = registrationService.registerUser(registrationFormDto);
			return "User registered successfully with userName: "+user.getUserName();
		}catch(Exception ex) {
			return ex.getMessage();
		}
	}
	
}
