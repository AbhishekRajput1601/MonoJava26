package org.abhishek.many_to_one.service;


import org.abhishek.many_to_one.dto.DepartmentRequestDto;
import org.abhishek.many_to_one.dto.DepartmentResponseDto;
import org.abhishek.many_to_one.dto.PageResponseDto;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto);

    List<DepartmentResponseDto> getAllDepartments();

    PageResponseDto<DepartmentResponseDto> getAllDepartmentsWithPagination(int pageNumber, int pageSize);

    DepartmentResponseDto getDepartmentById(Long id);

    DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto requestDto);

    void deleteDepartment(Long id);

}
