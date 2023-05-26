package com.studentdetails.test;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.studentdetails.controller.StudentDetailsController;
import com.studentdetails.model.Address;
import com.studentdetails.model.Grade;
import com.studentdetails.model.StudentDetailsResponse;

@SpringBootTest
class StudentDetailsControllerTests {

	@InjectMocks
	StudentDetailsController studentDetailsController;

	@Test
	void getStudentsDetailsTest() {

		StudentDetailsResponse response = new StudentDetailsResponse();
		response.setStudentNumber("960054419");
		response.setFirstName("Joe");
		response.setLastName("Smith");
		response.setPhoneNumber("8976543324");

		Address address = new Address();
		address.setCity("Brampton");
		address.setProvince("ON");
		address.setStreetName("Dixie Road");
		address.setStreetNumber("8200");

		Grade grade1 = new Grade();
		grade1.setGrade("math");
		grade1.setSubject("90");
		
		Grade grade2 = new Grade();
		grade2.setGrade("english");
		grade2.setSubject("90");
		


	}

}
