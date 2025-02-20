package com.microservice.curso.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.curso.entities.Course;
import com.microservice.curso.service.ICourseService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveStudent(@RequestBody Course course) {
		courseService.save(course);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Course>> findAllStudent(){
		String randomKey = generateRandomKey();
		kafkaTemplate.send("topicazo", randomKey, "hola que ase");
		return ResponseEntity.ok(courseService.findAll());
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ResponseEntity.ok(courseService.findById(id));
	}
	
	@CircuitBreaker(name = "student", fallbackMethod = "fallbackGetStudent")
	@GetMapping("/search-student/{idCourse}")
	public ResponseEntity<?> findStudensByIdCourse(@PathVariable Long idCourse){
		return ResponseEntity.ok(courseService.findStudentByIdCourse(idCourse));
	}
	
	private ResponseEntity<List<?>> fallbackGetStudent(@PathVariable Long idCourse, RuntimeException e){
		return new ResponseEntity("el estudiante a fallado", HttpStatus.OK);
	}

    private String generateRandomKey() {
        return "key-" + UUID.randomUUID();
    }
	
}
