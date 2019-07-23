package com.lithan.sb.dto;

import lombok.Data;

@Data
public class RegistrationFormDto {
	
	private String userName;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private long addressId;
	private String address1;
	private String address2;
	private String area;
	private String city;
	private String state;
	private String country;
	private String contactNo;
	private String email;
	private boolean active;
	private boolean primaryAddr;
	private String roleCode;
	
}
