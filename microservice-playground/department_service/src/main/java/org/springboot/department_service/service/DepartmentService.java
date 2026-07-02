package org.springboot.department_service.service;

import org.springboot.department_service.client.EmployeeFeignClient;
import org.springboot.department_service.dto.DepartmentRequest;
import org.springboot.department_service.dto.EmployeeResponse;
import org.springboot.department_service.entity.Department;
import org.springboot.department_service.exception.DepartmentNotFoundException;
import org.springboot.department_service.repository.DepartmentRepository;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department createDepartment(
            DepartmentRequest request) {

        Department department = new Department();

        department.setName(request.getName());

        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long departmentId) {

        return departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new DepartmentNotFoundException(
                                departmentId
                        )
                );
    }
}
