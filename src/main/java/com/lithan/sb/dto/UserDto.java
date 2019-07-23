package com.lithan.sb.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
	
	private String userName;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String roleRoleCode;
	
}