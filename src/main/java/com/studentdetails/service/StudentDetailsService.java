package com.studentdetails.service;

import com.studentdetails.exception.StudentDetailsException;
import com.studentdetails.model.StudentDetailsResponse;

public interface StudentDetailsService {

	StudentDetailsResponse getStudentDetailsResponse() throws StudentDetailsException, Exception;
}
