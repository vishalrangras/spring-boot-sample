package com.lithan.sb.dto;

import java.util.Date;
import lombok.Value;

@Value
public class UserDto {
	
	private long userId;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String roleRoleName;
	private String password;
	
}