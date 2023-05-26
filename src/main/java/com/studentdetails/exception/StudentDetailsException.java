package com.studentdetails.exception;

public class StudentDetailsException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	private int errorCode;

	public StudentDetailsException(int errorCode, String message) {

		super(message);
		this.errorCode = errorCode;
	}

	public StudentDetailsException(int errorCode, String message, RuntimeException ex) {

		super(message, ex);
		this.errorCode = errorCode;
	}

	public StudentDetailsException() {

		super();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
