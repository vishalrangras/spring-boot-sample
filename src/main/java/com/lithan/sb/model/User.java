package com.lithan.sb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	
	private Date dateOfBirth;
	
	@OneToMany
	private List<Address> addressList = new ArrayList<>();
	
	@ManyToOne
	private Role role;
	
	
}
