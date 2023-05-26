package com.studentdetails.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.studentdetails.client.StudentDetailsClient;
import com.studentdetails.exception.StudentDetailsException;
import com.studentdetails.model.StudentDetailsResponse;
import com.studentdetails.model.client.AccountClientResponse;
import com.studentdetails.model.client.AddressClientResponse;
import com.studentdetails.model.client.GradeClientResponse;
import com.studentdetails.service.StudentDetailsService;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {

	Logger logger = LoggerFactory.getLogger(StudentDetailsServiceImpl.class);

	StudentDetailsClient studentDetailsClient = new StudentDetailsClient();

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
	public StudentDetailsResponse getStudentDetailsResponse() throws StudentDetailsException, Exception {

		logger.info("In StudentDetailsService.");

		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
		StudentDetailsResponse studentDetailsResponse = null;

		// Get Account Details.
		AccountClientResponse accountClientResponse = studentDetailsClient.getAccountDetails(restTemplate, gson);

		// Get Address Details.
		AddressClientResponse addressClientResponsev = studentDetailsClient.getAddressDetails(restTemplate, gson);

		// Get Grade Details.
		GradeClientResponse gradeClientResponse = studentDetailsClient.getGradeDetails(restTemplate, gson);

		// Generate StudentDetails Response.
		studentDetailsResponse = generateStudentDetailsResponse(accountClientResponse, addressClientResponsev,
				gradeClientResponse);

		return studentDetailsResponse;
	}

	private StudentDetailsResponse generateStudentDetailsResponse(AccountClientResponse accountClientResponse,
			AddressClientResponse addressClientResponsev, GradeClientResponse gradeClientResponse) throws Exception {
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

}
