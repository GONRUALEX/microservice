package com.microservice.student.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.student.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
	
	@Query("SELECT s FROM Student s WHERE s.courseId = :idCourse")
	List<Student> findAllStudent(Long idCourse);
	
	// List<Student> findAllByCourseId(Long idCourse);
	
	
}
