package com.microservice.curso.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.curso.entities.Course;

@Repository
public interface ICourseRepository extends CrudRepository<Course, Long>{

}
