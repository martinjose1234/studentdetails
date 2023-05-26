package com.studentdetails.exception;

public class StudentDetailsException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public StudentDetailsException(String message) {

		super(message);
	}

	public StudentDetailsException() {

		super();
	}

}
