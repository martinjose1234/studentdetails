package com.studentdetails.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studentdetails.constants.StudentDetailsConstants;
import com.studentdetails.exception.StudentDetailsException;
import com.studentdetails.model.ErrorResponse;
import com.studentdetails.model.StudentDetailsResponse;
import com.studentdetails.service.StudentDetailsService;

//@Import({ CacheConfig.class })
//@EnableCaching
//@ImportAutoConfiguration(classes = { CacheAutoConfiguration.class, RedisAutoConfiguration.class })
@Controller
@RequestMapping(path = StudentDetailsConstants.BASE_PATH)
public class StudentDetailsController {

	Logger logger = LoggerFactory.getLogger(StudentDetailsController.class);

	@Autowired
	StudentDetailsService studentDetailsService;

	/**
	 * 
	 * API to get Student details.
	 *
	 * 
	 * 
	 * @return studentDetailsResponse
	 * 
	 */
	@GetMapping(StudentDetailsConstants.STUDENT_DETAILS_PATH)
	public ResponseEntity<Object> getStudentsDetails() {
		StudentDetailsResponse studentDetailsResponse = null;
		try {
			studentDetailsResponse = studentDetailsService.getStudentDetailsResponse();
		} catch (StudentDetailsException ex) {
			logger.error(StudentDetailsConstants.ERROR_TITLE + ex.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getErrorCode());
			return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
		}

		return new ResponseEntity<>(studentDetailsResponse, HttpStatus.OK);
	}

}
