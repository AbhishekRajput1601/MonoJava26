package org.abhishek.many_to_many.service;

import org.abhishek.many_to_many.dto.CourseRequestDto;
import org.abhishek.many_to_many.dto.CourseResponseDto;
import org.abhishek.many_to_many.dto.PageResponseDto;
import org.abhishek.many_to_many.dto.StudentResponseDto;

import java.util.List;

public interface CourseService {
    CourseResponseDto createCourse(CourseRequestDto dto);

    List<CourseResponseDto> getAllCourses();

    PageResponseDto<CourseResponseDto> getAllCoursesWithPagination(int pageNumber, int pageSize, String sortBy, String sortDirection);

    CourseResponseDto getCourseById(Long id);

    List<StudentResponseDto> getStudentsByCourseId(Long courseId);

    CourseResponseDto updateCourse(Long id, CourseRequestDto dto);

    void deleteCourse(Long id);
}

