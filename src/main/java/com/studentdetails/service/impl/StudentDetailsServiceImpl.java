package com.studentdetails.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.studentdetails.constants.StudentDetailsConstants;
import com.studentdetails.model.StudentDetailsResponse;
import com.studentdetails.model.client.AccountClientResponse;
import com.studentdetails.model.client.AddressClientResponse;
import com.studentdetails.model.client.GradeClientResponse;
import com.studentdetails.service.StudentDetailsService;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {

	@Override
	public StudentDetailsResponse getStudentDetailsResponse() {

		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();

		// Get Account Details.
		AccountClientResponse accountClientResponse = getAccountDetails(restTemplate, gson);

		// Get Address Details.
		AddressClientResponse addressClientResponsev = getAddressDetails(restTemplate, gson);

		// Get Grade Details.
		GradeClientResponse gradeClientResponse = getGradeDetails(restTemplate, gson);

		// Generate StudentDetails Response.
		StudentDetailsResponse studentDetailsResponse = generateStudentDetailsResponse(accountClientResponse,
				addressClientResponsev, gradeClientResponse);

		return studentDetailsResponse;
	}

	private StudentDetailsResponse generateStudentDetailsResponse(AccountClientResponse accountClientResponse,
			AddressClientResponse addressClientResponsev, GradeClientResponse gradeClientResponse) {
		StudentDetailsResponse studentDetailsResponse = new StudentDetailsResponse();

		// Populate Account Details into Response.
		studentDetailsResponse.setStudentNumber(accountClientResponse.getStudentNumber());
		studentDetailsResponse.setFirstName(accountClientResponse.getFirstName());
		studentDetailsResponse.setLastName(accountClientResponse.getLastName());
		studentDetailsResponse.setPhoneNumber(accountClientResponse.getPhoneNumber());

		// Populate Address Details into Response.
		if (accountClientResponse.getStudentNumber().equals(addressClientResponsev.getStudentNumber())) {
			studentDetailsResponse.setAddress(addressClientResponsev.getAddress());
		}

		// Populate Grade Details into Response.
		if (accountClientResponse.getStudentNumber().equals(gradeClientResponse.getStudentNumber())) {
			studentDetailsResponse.setGrades(gradeClientResponse.getGrades());
		}
		return studentDetailsResponse;
	}

	private GradeClientResponse getGradeDetails(RestTemplate restTemplate, Gson gson) {
		String uri = StudentDetailsConstants.GRADE_URI;
		String gradeJsonString = restTemplate.getForObject(uri, String.class);
		GradeClientResponse gradeClientResponse = gson.fromJson(gradeJsonString, GradeClientResponse.class);
		return gradeClientResponse;
	}

	private AddressClientResponse getAddressDetails(RestTemplate restTemplate, Gson gson) {
		String uri = StudentDetailsConstants.ADDRESS_URI;
		String addressJsonString = restTemplate.getForObject(uri, String.class);
		AddressClientResponse addressClientResponse = gson.fromJson(addressJsonString, AddressClientResponse.class);
		return addressClientResponse;
	}

	private AccountClientResponse getAccountDetails(RestTemplate restTemplate, Gson gson) {
		String uri = StudentDetailsConstants.ACCOUNT_URI;
		String accountJsonString = restTemplate.getForObject(uri, String.class);
		AccountClientResponse accountClientResponse = gson.fromJson(accountJsonString, AccountClientResponse.class);
		return accountClientResponse;
	}

}
