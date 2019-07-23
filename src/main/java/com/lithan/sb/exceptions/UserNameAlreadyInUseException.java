package com.lithan.sb.exceptions;


public class UserNameAlreadyInUseException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5201664548405181200L;
	private String userName;
	
	public UserNameAlreadyInUseException(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String getMessage() {
	
		StringBuilder sb = new StringBuilder();
		sb.append("Username : '");
		sb.append(this.userName);
		sb.append("' is already in use. Pick a different username.");
		return sb.toString();
	}
	
}
