package com.lithan.sb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	private Date dateOfBirth;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private List<Address> addressList = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

}
