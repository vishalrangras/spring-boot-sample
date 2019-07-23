package com.lithan.sb.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userName")
	private User user;
	
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
