package com.studentdetails.model.client;

import java.util.List;

import com.studentdetails.model.Grade;

public class GradeClientResponse {

	private String studentNumber;
	private List<Grade> grades;

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

}
