package com.lithan.sb.dto;

import lombok.Data;

@Data
public class AddressDto {
	
	private long addressId;
	private String userUserName;
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
	
}
