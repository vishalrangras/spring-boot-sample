package com.lithan.sb.exceptions;


public class UserNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4987350041655208588L;
	private String userName;
	
	public UserNotFoundException(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("User not found for update by username : '");
		sb.append(this.userName);
		return sb.toString();
	}

}
