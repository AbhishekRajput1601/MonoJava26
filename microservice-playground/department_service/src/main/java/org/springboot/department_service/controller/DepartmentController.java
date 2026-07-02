package org.springboot.department_service.controller;

import lombok.RequiredArgsConstructor;
import org.springboot.department_service.client.EmployeeRestTemplate;
import org.springboot.department_service.dto.DepartmentRequest;
import org.springboot.department_service.dto.DepartmentWithEmployeesResponse;
import org.springboot.department_service.dto.EmployeeResponse;
import org.springboot.department_service.entity.Department;
import org.springboot.department_service.service.DepartmentRestTemplateService;
import org.springboot.department_service.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor

public class DepartmentController {

    private final DepartmentService departmentService;
    private final EmployeeRestTemplate employeeRestTemplate;
    private final DepartmentRestTemplateService departmentRestTemplateService;

    @PostMapping
    public ResponseEntity<Department> createDepartment(
            @RequestBody DepartmentRequest request) {

        Department createdDepartment =
                departmentService.createDepartment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdDepartment);
    }


    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(
            @PathVariable Long departmentId) {

        Department department =
                departmentService.getDepartmentById(departmentId);

        return ResponseEntity.ok(department);
    }

    @GetMapping("{departmentId}/rest-template")
    public ResponseEntity<DepartmentWithEmployeesResponse> getEmployeeUsingRestTemplate(@PathVariable Long departmentId) {
        return ResponseEntity.ok(departmentRestTemplateService.getDepartmentWithEmployee(departmentId));
    }

    @GetMapping("/{departmentId}/feign")
    public ResponseEntity<List<EmployeeResponse>> getEmployees(@PathVariable Long departmentId) {
        List<EmployeeResponse> employees = departmentRestTemplateService.getEmployees(departmentId);

        return ResponseEntity.ok(employees);
    }
}