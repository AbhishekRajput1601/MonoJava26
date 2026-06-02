package org.abhishek.many_to_many.repository;

import org.abhishek.many_to_many.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    List<Student> findByCoursesId(Long courseId);

    Page<Student> findAll(Pageable pageable);
}

