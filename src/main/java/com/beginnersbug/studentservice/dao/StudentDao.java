package com.beginnersbug.studentservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beginnersbug.studentservice.model.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {

}
