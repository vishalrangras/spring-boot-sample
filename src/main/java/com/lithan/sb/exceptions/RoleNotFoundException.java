package com.lithan.sb.exceptions;

public class RoleNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6946942949342340559L;

	public RoleNotFoundException(String roleCode) {
		this.roleCode = roleCode;
	}
	
	private String roleCode;
	
	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("Role Code: '");
		sb.append(this.roleCode);
		sb.append("' ");
		sb.append("not an acceptable value. ");
		sb.append("Acceptable values are: 'Admin', 'User' and 'Moderator'");
		return sb.toString();
	}
	
}
