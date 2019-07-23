package com.lithan.sb.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Role {
	
	@Id
	private String roleCode;
	private String roleName;
	
}
