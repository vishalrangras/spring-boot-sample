package com.lithan.sb.dto;

import lombok.Value;

@Value
public class AddressDto {
	
	private long addressId;
	private String userFirstName;
	private String userLastName;
	private String address1;
	private String address2;
	private String area;
	private String city;
	private String state;
	private String country;
	private String contactNo;
	private String email;
	private boolean active;
	private boolean primary;
	
}
