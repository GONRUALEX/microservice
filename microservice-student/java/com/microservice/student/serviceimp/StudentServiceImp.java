package com.microservice.student.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.student.entity.Student;
import com.microservice.student.persistence.StudentRepository;
import com.microservice.student.service.IStudentService;

@Service
public class StudentServiceImp implements IStudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> findAll(){
		return (List<Student>) studentRepository.findAll();
	}
	
	@Override
	public Student findById(Long id) {
		return studentRepository.findById(id).orElseThrow();
	}
	
	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	public List<Student> findByIdCourse(Long idCourse) {
		return studentRepository.findAllStudent(idCourse);
	}
}
