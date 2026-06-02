package org.abhishek.mapping_with_swagger.repositories;

import org.abhishek.mapping_with_swagger.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}