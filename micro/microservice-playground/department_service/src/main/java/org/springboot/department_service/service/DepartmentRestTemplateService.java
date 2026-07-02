package org.springboot.department_service.service;

import lombok.RequiredArgsConstructor;
import org.springboot.department_service.client.EmployeeRestTemplate;
import org.springboot.department_service.dto.DepartmentWithEmployeesResponse;
import org.springboot.department_service.dto.EmployeeResponse;
import org.springboot.department_service.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentRestTemplateService {

    private final DepartmentService departmentService;
    private final EmployeeRestTemplate employeeRestTemplate;

    public DepartmentWithEmployeesResponse getDepartmentWithEmployee(Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);

        List<EmployeeResponse> employees = employeeRestTemplate.getEmployeeByDepartmentId(departmentId);

        return new DepartmentWithEmployeesResponse(department.getId(), department.getName(), employees);
    }


}
