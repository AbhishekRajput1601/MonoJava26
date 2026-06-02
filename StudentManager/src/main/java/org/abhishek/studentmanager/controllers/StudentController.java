package org.abhishek.studentmanager.controllers;

import java.util.List;

import org.abhishek.studentmanager.entities.Student;
import org.abhishek.studentmanager.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
@Validated
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("getAll")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}

	@PostMapping("/create")
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
		Student savedStudent = studentService.createStudent(student);
		return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
	}

	@PostMapping("/createMany")
	public ResponseEntity<List<Student>> createManyStudents(@Valid @RequestBody List<Student> students) {
		List<Student> savedStudents = studentService.createManyStudents(students);
		return new ResponseEntity<>(savedStudents, HttpStatus.CREATED);
	}

	@PutMapping("update/{id}")
	public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student studentDetails) {
		return studentService.updateStudent(id, studentDetails);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}
}
