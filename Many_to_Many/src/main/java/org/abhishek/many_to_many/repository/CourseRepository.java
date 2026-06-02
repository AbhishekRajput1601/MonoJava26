package org.abhishek.many_to_many.repository;

import org.abhishek.many_to_many.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCourseCode(String courseCode);

    boolean existsByCourseCodeAndIdNot(String courseCode, Long id);

    List<Course> findByStudentsId(Long studentId);

    Page<Course> findAll(Pageable pageable);
}

