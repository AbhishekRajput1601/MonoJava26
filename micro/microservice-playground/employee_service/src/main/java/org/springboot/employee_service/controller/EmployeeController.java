package org.springboot.employee_service.controller;

import java.util.List;

import org.springboot.employee_service.dto.EmployeeRequest;
import org.springboot.employee_service.dto.EmployeeResponse;
import org.springboot.employee_service.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        EmployeeResponse response =
                employeeService.createEmployee(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(employeeId)
        );
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<EmployeeResponse>>
    getEmployeesByDepartmentId(
            @PathVariable Long departmentId) {

        return ResponseEntity.ok(
                employeeService.getEmployeesByDepartmentId(
                        departmentId
                )
        );
    }
}

