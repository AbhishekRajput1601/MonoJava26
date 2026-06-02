package org.abhishek.mapping_with_swagger.services;


import org.abhishek.mapping_with_swagger.dtos.PageResponseDto;
import org.abhishek.mapping_with_swagger.dtos.StudentRequestDto;
import org.abhishek.mapping_with_swagger.dtos.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto requestDto);

    List<StudentResponseDto> getAllStudents();

    PageResponseDto<StudentResponseDto> getAllStudentsWithPagination(int pageNumber, int pageSize);

    StudentResponseDto getStudentById(Long id);

    StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto);

    void deleteStudent(Long id);
}
