package com.microservice.curso.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.curso.dto.StudentDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name="msvc-student")
public interface StudentClient {

	@GetMapping("/api/student/search-ny-course/{idCourse}")
	List<StudentDto> findAllStudentByCourse(@PathVariable Long idCourse);

}
