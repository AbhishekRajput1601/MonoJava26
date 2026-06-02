package org.abhishek.studentmanager.services;

import java.util.List;

import org.abhishek.studentmanager.entities.Student;
import org.abhishek.studentmanager.exceptions.StudentNotFoundException;
import org.abhishek.studentmanager.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Student getStudentById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
	}

	@Override
	public Student createStudent(Student student) {
		student.setSId(null);
		return studentRepository.save(student);
	}

	@Override
	public List<Student> createManyStudents(List<Student> students) {
		students.forEach(student -> student.setSId(null));
		return studentRepository.saveAll(students);
	}

	@Override
	public Student updateStudent(Long id, Student studentDetails) {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));

		existingStudent.setSName(studentDetails.getSName());
		existingStudent.setSAge(studentDetails.getSAge());
		existingStudent.setSDepartment(studentDetails.getSDepartment());

		return studentRepository.save(existingStudent);
	}

	@Override
	public void deleteStudent(Long id) {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));

		studentRepository.delete(existingStudent);
	}

}

