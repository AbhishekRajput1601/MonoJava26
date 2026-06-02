package org.abhishek.many_to_many.service;

import org.abhishek.many_to_many.dto.CourseResponseDto;
import org.abhishek.many_to_many.dto.PageResponseDto;
import org.abhishek.many_to_many.dto.StudentRequestDto;
import org.abhishek.many_to_many.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto dto);

    List<StudentResponseDto> getAllStudents();

    PageResponseDto<StudentResponseDto> getAllStudentsWithPagination(int pageNumber, int pageSize, String sortBy, String sortDirection);

    StudentResponseDto getStudentById(Long id);

    List<CourseResponseDto> getCoursesByStudentId(Long studentId);

    StudentResponseDto updateStudent(Long id, StudentRequestDto dto);

    StudentResponseDto assignCourseToStudent(Long studentId, Long courseId);

    StudentResponseDto removeCourseFromStudent(Long studentId, Long courseId);

    void deleteStudent(Long id);
}

