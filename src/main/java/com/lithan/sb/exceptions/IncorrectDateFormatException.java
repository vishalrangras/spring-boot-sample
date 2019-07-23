package com.lithan.sb.exceptions;


public class IncorrectDateFormatException extends RuntimeException{
	
	private static final long serialVersionUID = -8270862013290118962L;
	private String expectedPattern;
	private String fieldName;
	
	public IncorrectDateFormatException(String expectedPattern, String fieldName) {
		this.expectedPattern = expectedPattern;
		this.fieldName = fieldName;
	}
	
	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("Incorrect Date Format for the field '");
		sb.append(this.fieldName);
		sb.append("'. Expected pattern is: '");
		sb.append(this.expectedPattern);
		sb.append("'.");
		return super.getMessage();
	}
}
