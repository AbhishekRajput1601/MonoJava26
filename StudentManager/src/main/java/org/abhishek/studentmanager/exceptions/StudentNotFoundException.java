package org.abhishek.studentmanager.exceptions;

public class StudentNotFoundException extends RuntimeException {

	public StudentNotFoundException(Long id) {
		super("Student not found with id: " + id);
	}
}


