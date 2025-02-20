package com.microservice.curso.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.curso.client.StudentClient;
import com.microservice.curso.dto.StudentDto;
import com.microservice.curso.entities.Course;
import com.microservice.curso.http.response.StudentByCourseResponse;
import com.microservice.curso.persistence.ICourseRepository;
import com.microservice.curso.service.ICourseService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CourseServiceImp implements ICourseService{

	@Autowired
	private ICourseRepository courseRepository;
	
	@Autowired
	private StudentClient studentClient;
	
	@Override
	public List<Course> findAll() {
		return (List<Course>) courseRepository.findAll();
	}

	@Override
	public Course findById(Long id) {
		return courseRepository.findById(id).orElseThrow();
	}

	@Override
	public void save(Course course) {
		
		courseRepository.save(course);
		
	}

	@Override
	public StudentByCourseResponse findStudentByIdCourse(Long idCourse) {
		Course course= courseRepository.findById(idCourse).orElse(new Course());
		List<StudentDto> studentDtoList = studentClient.findAllStudentByCourse(idCourse);
		return StudentByCourseResponse.builder()
				.courseName(course.getName())
				.teacher(course.getTeacher())
				.studentDTOList(studentDtoList)
				.build();
	}
	
}
