package com.studentdetails.model.client;

import com.studentdetails.model.Address;

public class AddressClientResponse {
	
	private String studentNumber;
	private Address address;

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
