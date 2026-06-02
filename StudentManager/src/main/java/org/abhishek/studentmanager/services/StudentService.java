package org.abhishek.studentmanager.services;

import java.util.List;

import org.abhishek.studentmanager.entities.Student;

public interface StudentService {

	List<Student> getAllStudents();

	Student getStudentById(Long id);

	Student createStudent(Student student);

	List<Student> createManyStudents(List<Student> students);

	Student updateStudent(Long id, Student studentDetails);

	void deleteStudent(Long id);

}

