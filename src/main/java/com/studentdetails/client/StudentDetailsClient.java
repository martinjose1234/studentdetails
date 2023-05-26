package com.studentdetails.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.studentdetails.constants.StudentDetailsConstants;
import com.studentdetails.exception.StudentDetailsException;
import com.studentdetails.model.client.AccountClientResponse;
import com.studentdetails.model.client.AddressClientResponse;
import com.studentdetails.model.client.GradeClientResponse;

public class StudentDetailsClient {

	Logger logger = LoggerFactory.getLogger(StudentDetailsClient.class);

	public GradeClientResponse getGradeDetails(RestTemplate restTemplate, Gson gson)
			throws StudentDetailsException, Exception {
		String uri = StudentDetailsConstants.GRADE_URI;
		GradeClientResponse gradeClientResponse = null;
		String gradeJsonString = null;
		try {
			gradeJsonString = restTemplate.getForObject(uri, String.class);
		} catch (Exception ex) {
			logger.error("Error while accessing Grades service : " + ex);
			throw new StudentDetailsException(StudentDetailsConstants.GRADES_SERVICE_UNAVAILABLE);
		}

		logger.info("Rsponse from Grades service : " + gradeJsonString);

		try {
			gradeClientResponse = gson.fromJson(gradeJsonString, GradeClientResponse.class);
		} catch (JsonParseException ex) {
			logger.error("Error while parsing Grade service response : " + ex);
			throw new StudentDetailsException(StudentDetailsConstants.WRONG_RESPONSE_FROM_GRADES_SERVICE);
		}
		return gradeClientResponse;
	}

	public AddressClientResponse getAddressDetails(RestTemplate restTemplate, Gson gson)
			throws StudentDetailsException, Exception {
		String uri = StudentDetailsConstants.ADDRESS_URI;
		AddressClientResponse addressClientResponse = null;
		String addressJsonString = null;

		try {
			addressJsonString = restTemplate.getForObject(uri, String.class);
		} catch (Exception ex) {
			logger.error("Error while accessing Address service : " + ex);
			throw new StudentDetailsException(StudentDetailsConstants.ADDRESS_SERVICE_UNAVAILABLE);
		}
		logger.info("Rsponse from Address service : " + addressJsonString);

		try {
			addressClientResponse = gson.fromJson(addressJsonString, AddressClientResponse.class);
		} catch (JsonParseException ex) {
			logger.error("Error while parsing Address service response : " + ex);
			throw new StudentDetailsException(StudentDetailsConstants.WRONG_RESPONSE_FROM_ADDRESS_SERVICE);
		}
		return addressClientResponse;
	}

	public AccountClientResponse getAccountDetails(RestTemplate restTemplate, Gson gson)
			throws StudentDetailsException, Exception {
		String uri = StudentDetailsConstants.ACCOUNT_URI;
		AccountClientResponse accountClientResponse = null;
		String accountJsonString = null;
		try {
			accountJsonString = restTemplate.getForObject(uri, String.class);
		} catch (Exception ex) {
			logger.error("Error while accessing Account service : " + ex);
			throw new StudentDetailsException(StudentDetailsConstants.ACCOUNT_SERVICE_UNAVAILABLE);
		}
		logger.info("Rsponse from Account service : " + accountJsonString);

		try {
			accountClientResponse = gson.fromJson(accountJsonString, AccountClientResponse.class);
		} catch (JsonParseException ex) {
			logger.error("Error while parsing Account service response : " + ex);
			throw new StudentDetailsException(StudentDetailsConstants.WRONG_RESPONSE_FROM_ACCOUNT_SERVICE);
		}

		return accountClientResponse;
	}

}
