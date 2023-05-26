package com.studentdetails.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.studentdetails.exception.StudentDetailsException;
import com.studentdetails.model.Address;
import com.studentdetails.model.Grade;
import com.studentdetails.model.StudentDetailsResponse;
import com.studentdetails.service.StudentDetailsService;
import com.studentdetails.service.impl.StudentDetailsServiceImpl;

@SpringBootTest
class StudentDetailsServiceTests {

	@InjectMocks
	StudentDetailsService studentDetailsService = new StudentDetailsServiceImpl();

	@Test
	void getStudentsDetailsTest() throws StudentDetailsException, Exception {

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
		response.setAddress(address);

		List<Grade> grades = new ArrayList<>();

		Grade grade1 = new Grade();
		grade1.setGrade("math");
		grade1.setSubject("90");
		grades.add(grade1);

		Grade grade2 = new Grade();
		grade2.setGrade("english");
		grade2.setSubject("90");
		grades.add(grade2);

		response.setGrades(grades);

		StudentDetailsResponse actualResult = studentDetailsService.getStudentDetailsResponse();

		assertEquals(response.getFirstName(), actualResult.getFirstName());
		assertEquals(response.getLastName(), actualResult.getLastName());
		assertEquals(response.getAddress().getCity(), actualResult.getAddress().getCity());
		assertEquals(actualResult.getGrades().size(), response.getGrades().size());
	}

}
