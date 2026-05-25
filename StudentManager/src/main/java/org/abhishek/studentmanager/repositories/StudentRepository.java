package org.abhishek.studentmanager.repositories;

import org.abhishek.studentmanager.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
