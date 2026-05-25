package org.abhishek.studentmanager.controllers;

import java.util.List;
import java.util.Objects;

import org.abhishek.studentmanager.entities.Student;
import org.abhishek.studentmanager.exceptions.StudentNotFoundException;
import org.abhishek.studentmanager.repositories.StudentRepository;
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

	private final StudentRepository studentRepository;

	@Autowired
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@GetMapping("getAll")
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
	}

	@PostMapping("/create")
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
		student.setSId(null);
		Student savedStudent = studentRepository.save(student);
		return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
	}

	@PostMapping("/createMany")
	public ResponseEntity<List<Student>> createManyStudents(@Valid @RequestBody List<@Valid Student> students) {
		if (students.isEmpty()) {
			throw new IllegalArgumentException("Student list cannot be empty");
		}

		if (students.stream().anyMatch(Objects::isNull)) {
			throw new IllegalArgumentException("Student list cannot contain null elements");
		}

		students.forEach(student -> student.setSId(null));
		List<Student> savedStudents = studentRepository.saveAll(students);
		return new ResponseEntity<>(savedStudents, HttpStatus.CREATED);
	}

	@PutMapping("update/{id}")
	public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student studentDetails) {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));

		existingStudent.setSName(studentDetails.getSName());
		existingStudent.setSAge(studentDetails.getSAge());
		existingStudent.setSDepartment(studentDetails.getSDepartment());

		return studentRepository.save(existingStudent);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));

		studentRepository.delete(existingStudent);
		return ResponseEntity.noContent().build();
	}
}
