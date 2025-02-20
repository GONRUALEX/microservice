package com.microservice.curso.service;

import java.util.List;

import com.microservice.curso.entities.Course;
import com.microservice.curso.http.response.StudentByCourseResponse;

public interface ICourseService {

	List<Course> findAll();

	Course findById(Long id);
	
	void save(Course course);
	
	StudentByCourseResponse findStudentByIdCourse(Long idCourse);
}
