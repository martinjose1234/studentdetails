package com.studentdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studentdetails.config.CacheConfig;
import com.studentdetails.model.StudentDetailsResponse;
import com.studentdetails.service.StudentDetailsService;

@Import({ CacheConfig.class, StudentDetailsService.class })
@EnableCaching
@ImportAutoConfiguration(classes = { CacheAutoConfiguration.class, RedisAutoConfiguration.class })
@Controller
@RequestMapping(path = "/studentdetails")
public class StudentDetailsController {

	@Autowired
	StudentDetailsService studentDetailsService;

	@GetMapping("/getStudentDetails")
	public ResponseEntity<Object> getMockableio() {

		StudentDetailsResponse mockableIoResponse = studentDetailsService.getStudentDetailsResponse();

		return new ResponseEntity<>(mockableIoResponse, HttpStatus.OK);
	}

}
