package org.springboot.employee_service.service;

import java.util.List;

import org.springboot.employee_service.dto.EmployeeRequest;
import org.springboot.employee_service.dto.EmployeeResponse;
import org.springboot.employee_service.entity.Employee;
import org.springboot.employee_service.exception.EmployeeNotFoundException;
import org.springboot.employee_service.repository.EmployeeRepository;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeResponse createEmployee(EmployeeRequest request) {

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartmentId(request.getDepartmentId());

        Employee savedEmployee = employeeRepository.save(employee);

        return convertToResponse(savedEmployee);
    }

    public EmployeeResponse getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(
                        () -> new EmployeeNotFoundException(employeeId)
                );

        return convertToResponse(employee);
    }

    public List<EmployeeResponse> getEmployeesByDepartmentId(
            Long departmentId) {

        return employeeRepository.findByDepartmentId(departmentId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    private EmployeeResponse convertToResponse(Employee employee) {

        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartmentId()
        );
    }
}
