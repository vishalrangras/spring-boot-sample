package com.lithan.sb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lithan.sb.dto.AddressDto;
import com.lithan.sb.dto.RegistrationFormDto;
import com.lithan.sb.dto.UserDto;
import com.lithan.sb.model.User;
import com.lithan.sb.projection.AddressProjection;
import com.lithan.sb.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/initializeroles")
	public String initializeRoles() {
		if(userService.initializeRoles()) {
			return "Role initialization successful";
		}else {
			return "Role initialization failed, contact administrator.";
		}
	}
	
	@PostMapping("/saveuser")
	public String registerUser(@RequestBody RegistrationFormDto registrationFormDto) {
		try{
			User user = userService.registerUser(registrationFormDto, false);
			return "User registered successfully with userName: "+user.getUserName();
		}catch(Exception ex) {
			return ex.getMessage();
		}
	}
	
	@PutMapping("/updateuser")
	public String updateUser(@RequestBody RegistrationFormDto registrationFormDto) {
		try {
			User user = userService.registerUser(registrationFormDto, true);
			return "User updated successfully with userName: "+user.getUserName();
		}catch (Exception ex) {
			return ex.getMessage();
		}
	}
	
	@GetMapping("/showuser")
	public UserDto showUser(@RequestParam("username") String userName) {
			return userService.showUser(userName);
	}
	
	@PostMapping("/saveaddress")
	public String saveAddress(@RequestBody AddressDto addressDto) {
		return userService.saveAddress(addressDto);
	}
	
	@GetMapping("/listusers")
	public List<UserDto> listUsers(){
		return userService.listUsers();
	}
	
	@GetMapping("/listaddressesbyuser/{username}")
	public List<AddressProjection> listAddressesByUser(@PathVariable("username") String userName){
		return userService.listAddressesByUser(userName);
	}
	
	@DeleteMapping("/deleteuser/{username}")
	public String deleteUser(@PathVariable("username") String userName) {
		userService.deleteUser(userName);
		return "User deleted successfully with username: "+userName;
	}
	
	@DeleteMapping("/deleteaddress/{addressId}")
	public String deleteAddress(@PathVariable("addressId") long addressId) {
		userService.deleteAddress(addressId);
		return "Address deleted successfully with id: "+addressId;
	}
	
}
