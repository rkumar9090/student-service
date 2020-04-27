package com.beginnersbug.studentservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beginnersbug.studentservice.dao.StudentDao;
import com.beginnersbug.studentservice.model.Student;

@RestController()
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	StudentDao studentsDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Student> getStudentsList() {
		return studentsDao.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable("id") String id) {
		Optional<Student> findById = studentsDao.findById(Long.parseLong(id));
		return findById.get();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody Student student) {
		studentsDao.save(student);
		return new ResponseEntity<String>("Student Created Successfully", HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> updateUser(@Valid @RequestBody Student student) {
		Student updatedStudent = studentsDao.findById(student.getId()).get();
		updatedStudent.setFirstname(student.getFirstname());
		updatedStudent.setLastname(student.getLastname());
		studentsDao.save(updatedStudent);
		return new ResponseEntity<String>("Student Updated Sucessfully ", HttpStatus.NO_CONTENT);

	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAllStudents() {
		studentsDao.deleteAll();
		return new ResponseEntity<String>("Student deleted ", HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteStudent(@PathVariable("id") String id) {
		studentsDao.deleteById(Long.parseLong(id));
		return new ResponseEntity<String>("Student deleted ", HttpStatus.NO_CONTENT);
	}

}
