package com.studentdetails.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.studentdetails.constants.StudentDetailsConstants;
import com.studentdetails.controller.StudentDetailsController;
import com.studentdetails.exception.StudentDetailsException;
import com.studentdetails.model.StudentDetailsResponse;
import com.studentdetails.model.client.AccountClientResponse;
import com.studentdetails.model.client.AddressClientResponse;
import com.studentdetails.model.client.GradeClientResponse;
import com.studentdetails.service.StudentDetailsService;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {

	Logger logger = LoggerFactory.getLogger(StudentDetailsController.class);

	/**
	 * 
	 * Service for getting Student details.
	 *
	 * 
	 * 
	 * @return studentDetailsResponse
	 * 
	 */
	@Cacheable(value = "itemCache")
	@Override
	public StudentDetailsResponse getStudentDetailsResponse() {

		logger.info("In StudentDetailsService.");

		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
		StudentDetailsResponse studentDetailsResponse = null;

		// Get Account Details.
		AccountClientResponse accountClientResponse = getAccountDetails(restTemplate, gson);

		// Get Address Details.
		AddressClientResponse addressClientResponsev = getAddressDetails(restTemplate, gson);

		// Get Grade Details.
		GradeClientResponse gradeClientResponse = getGradeDetails(restTemplate, gson);

		// Generate StudentDetails Response.
		studentDetailsResponse = generateStudentDetailsResponse(accountClientResponse, addressClientResponsev,
				gradeClientResponse);

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
		if (null != accountClientResponse.getStudentNumber()
				&& accountClientResponse.getStudentNumber().equals(addressClientResponsev.getStudentNumber())) {
			studentDetailsResponse.setAddress(addressClientResponsev.getAddress());
		}

		// Populate Grade Details into Response.
		if (null != accountClientResponse.getStudentNumber()
				&& accountClientResponse.getStudentNumber().equals(gradeClientResponse.getStudentNumber())) {
			studentDetailsResponse.setGrades(gradeClientResponse.getGrades());
		}
		return studentDetailsResponse;
	}

	private GradeClientResponse getGradeDetails(RestTemplate restTemplate, Gson gson) {
		String uri = StudentDetailsConstants.GRADE_URI;
		GradeClientResponse gradeClientResponse = null;
		String gradeJsonString = restTemplate.getForObject(uri, String.class);
		logger.info("Rsponse from Grades service : " + gradeJsonString);

		if (null == gradeJsonString || gradeJsonString.isEmpty()) {
			throw new StudentDetailsException(HttpStatus.SERVICE_UNAVAILABLE.value(),
					StudentDetailsConstants.GRADES_SERVICE_UNAVAILABLE);
		}
		try {
			gradeClientResponse = gson.fromJson(gradeJsonString, GradeClientResponse.class);
		} catch (JsonParseException ex) {
			throw new StudentDetailsException(HttpStatus.SERVICE_UNAVAILABLE.value(),
					StudentDetailsConstants.WRONG_RESPONSE_FROM_GRADES_SERVICE, ex);
		}
		return gradeClientResponse;
	}

	private AddressClientResponse getAddressDetails(RestTemplate restTemplate, Gson gson) {
		String uri = StudentDetailsConstants.ADDRESS_URI;
		AddressClientResponse addressClientResponse = null;
		String addressJsonString = restTemplate.getForObject(uri, String.class);
		logger.info("Rsponse from Address service : " + addressJsonString);

		if (null == addressJsonString || addressJsonString.isEmpty()) {
			throw new StudentDetailsException(HttpStatus.SERVICE_UNAVAILABLE.value(),
					StudentDetailsConstants.ADDRESS_SERVICE_UNAVAILABLE);
		}
		try {
			addressClientResponse = gson.fromJson(addressJsonString, AddressClientResponse.class);
		} catch (JsonParseException ex) {
			throw new StudentDetailsException(HttpStatus.SERVICE_UNAVAILABLE.value(),
					StudentDetailsConstants.WRONG_RESPONSE_FROM_ADDRESS_SERVICE, ex);
		}
		return addressClientResponse;
	}

	private AccountClientResponse getAccountDetails(RestTemplate restTemplate, Gson gson) {
		String uri = StudentDetailsConstants.ACCOUNT_URI;
		AccountClientResponse accountClientResponse = null;
		String accountJsonString = restTemplate.getForObject(uri, String.class);
		logger.info("Rsponse from Account service : " + accountJsonString);

		if (null == accountJsonString || accountJsonString.isEmpty()) {
			throw new StudentDetailsException(HttpStatus.SERVICE_UNAVAILABLE.value(),
					StudentDetailsConstants.ACCOUNT_SERVICE_UNAVAILABLE);
		}

		try {
			accountClientResponse = gson.fromJson(accountJsonString, AccountClientResponse.class);
		} catch (JsonParseException ex) {
			throw new StudentDetailsException(HttpStatus.SERVICE_UNAVAILABLE.value(),
					StudentDetailsConstants.WRONG_RESPONSE_FROM_ACCOUNT_SERVICE, ex);
		}

		return accountClientResponse;
	}

}
