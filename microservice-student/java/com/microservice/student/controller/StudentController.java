package com.microservice.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.student.entity.Student;
import com.microservice.student.service.IStudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private IStudentService studentService;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveStudent(@RequestBody Student student) {
		studentService.save(student);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Student>> findAllStudent(){
		return ResponseEntity.ok(studentService.findAll());
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<Student> findById(@PathVariable Long id){
		return ResponseEntity.ok(studentService.findById(id));
	}
	
	@GetMapping("/search-ny-course/{idCourse}")
	public ResponseEntity<?> findByIdCourse(@PathVariable Long idCourse){
		return ResponseEntity.ok(studentService.findByIdCourse(idCourse));  
	}
}
