package org.abhishek.many_to_one.service;


import lombok.extern.slf4j.Slf4j;
import org.abhishek.many_to_one.dto.*;
import org.abhishek.many_to_one.exception.DuplicateResourceException;
import org.abhishek.many_to_one.exception.ResourceNotFoundException;
import org.abhishek.many_to_one.model.Department;
import org.abhishek.many_to_one.model.Employee;
import org.abhishek.many_to_one.repository.DepartmentRepository;
import org.abhishek.many_to_one.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {
        log.info("Creating department: {}", requestDto.getDepartmentName());

        // Check if department name already exists
        if (departmentRepository.existsByDepartmentName(requestDto.getDepartmentName())) {
            log.error("Department name already exists : {}", requestDto.getDepartmentName());
            throw new DuplicateResourceException("Department name already exists: " + requestDto.getDepartmentName());
        }

        // Validate employee emails for duplicates
        validateEmployeeEmailsForCreate(requestDto.getEmployees());

        // Create department
        Department department = new Department();
        department.setDepartmentName(requestDto.getDepartmentName());
        department.setLocation(requestDto.getLocation());

        // Create and attach employees
        attachEmployeesToDepartment(department, requestDto.getEmployees());

        // Save department
        department = departmentRepository.save(department);
        log.info("Department created successfully with ID: {}", department.getId());

        return mapDepartmentToResponseDto(department);
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        log.info("Fetching all departments");

        List<Department> departments = departmentRepository.findAll();
        List<DepartmentResponseDto> dtoList = departments.stream()
                .map(this::mapDepartmentToResponseDto)
                .collect(Collectors.toList());

        log.info("Retrieved {} departments", dtoList.size());
        return dtoList;
    }

    @Override
    public PageResponseDto<DepartmentResponseDto> getAllDepartmentsWithPagination(int pageNumber, int pageSize) {
        log.info("Fetching departments with pagination - pageNumber: {}, pageSize: {}", pageNumber, pageSize);

        validatePagination(pageNumber, pageSize);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Department> page = departmentRepository.findAll(pageable);

        List<DepartmentResponseDto> content = page.getContent().stream()
                .map(this::mapDepartmentToResponseDto)
                .collect(Collectors.toList());

        PageResponseDto<DepartmentResponseDto> response = new PageResponseDto<>();
        response.setContent(content);
        response.setPageNumber(pageNumber);
        response.setPageSize(pageSize);
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        log.info("Retrieved {} departments for page {}", content.size(), pageNumber);
        return response;
    }

    @Override
    public DepartmentResponseDto getDepartmentById(Long id) {
        log.info("Fetching department by ID: {}", id);

        Department department = findDepartmentById(id);
        log.info("Department found: {}", id);

        return mapDepartmentToResponseDto(department);
    }

    @Override
    public DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto requestDto) {
        log.info("Updating department with ID: {}", id);

        Department department = findDepartmentById(id);

        // Check if new department name already exists (excluding current department)
        if (!department.getDepartmentName().equals(requestDto.getDepartmentName()) &&
                departmentRepository.existsByDepartmentNameAndIdNot(requestDto.getDepartmentName(), id)) {
            log.error("Department name already exists: {}", requestDto.getDepartmentName());
            throw new DuplicateResourceException("Department name already exists: " + requestDto.getDepartmentName());
        }

        // Validate employee emails for update
        validateEmployeeEmailsForUpdate(department, requestDto.getEmployees());

        // Update department details
        department.setDepartmentName(requestDto.getDepartmentName());
        department.setLocation(requestDto.getLocation());

        // Update employees
        updateEmployees(department, requestDto.getEmployees());

        department = departmentRepository.save(department);
        log.info("Department updated successfully: {}", id);

        return mapDepartmentToResponseDto(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        log.info("Deleting department with ID: {}", id);

        Department department = findDepartmentById(id);
        departmentRepository.delete(department);

        log.info("Department deleted successfully: {}", id);
    }

    // Private helper methods

    private Department findDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Department not found with ID: {}", id);
                    return new ResourceNotFoundException("Department not found with ID: " + id);
                });
    }

    private void validateEmployeeEmailsForCreate(List<EmployeeRequestDto> employees) {
        Set<String> seenEmails = new HashSet<>();
        for (EmployeeRequestDto employee : employees) {
            if (!seenEmails.add(employee.getEmail())) {
                log.error("Duplicate employee email in request : {}", employee.getEmail());
                throw new DuplicateResourceException("Employee email already exists in request: " + employee.getEmail());
            }
            if (employeeRepository.existsByEmail(employee.getEmail())) {
                log.error("Employee email already exists : {}", employee.getEmail());
                throw new DuplicateResourceException("Employee email already exists: " + employee.getEmail());
            }
        }
    }

    private void validateEmployeeEmailsForUpdate(Department department, List<EmployeeRequestDto> employees) {
        Set<String> seenEmails = new HashSet<>();
        Set<String> existingEmails = department.getEmployees().stream()
                .map(Employee::getEmail)
                .collect(Collectors.toSet());

        for (EmployeeRequestDto employee : employees) {
            if (!seenEmails.add(employee.getEmail())) {
                log.error("Duplicate employee email in request: {}", employee.getEmail());
                throw new DuplicateResourceException("Employee email already exists in request: " + employee.getEmail());
            }

            if (!existingEmails.contains(employee.getEmail()) && employeeRepository.existsByEmail(employee.getEmail())) {
                log.error("Employee email already exists: {}", employee.getEmail());
                throw new DuplicateResourceException("Employee email already exists: " + employee.getEmail());
            }
        }
    }

    private void attachEmployeesToDepartment(Department department, List<EmployeeRequestDto> employeeRequests) {
        List<Employee> employees = employeeRequests.stream()
                .map(dto -> {
                    Employee employee = new Employee();
                    employee.setEmployeeName(dto.getEmployeeName());
                    employee.setEmail(dto.getEmail());
                    employee.setSalary(dto.getSalary());
                    employee.setDepartment(department);
                    return employee;
                })
                .collect(Collectors.toList());

        department.setEmployees(employees);
    }

    private void updateEmployees(Department department, List<EmployeeRequestDto> employeeRequests) {
        List<Employee> currentEmployees = department.getEmployees();
        Set<String> requestedEmails = employeeRequests.stream()
                .map(EmployeeRequestDto::getEmail)
                .collect(Collectors.toSet());

        currentEmployees.removeIf(emp -> !requestedEmails.contains(emp.getEmail()));

        for (EmployeeRequestDto dto : employeeRequests) {
            Employee employee = currentEmployees.stream()
                    .filter(e -> e.getEmail().equals(dto.getEmail()))
                    .findFirst()
                    .orElseGet(() -> {
                        Employee newEmployee = new Employee();
                        newEmployee.setDepartment(department);
                        currentEmployees.add(newEmployee);
                        return newEmployee;
                    });

            employee.setEmployeeName(dto.getEmployeeName());
            employee.setEmail(dto.getEmail());
            employee.setSalary(dto.getSalary());
            employee.setDepartment(department);
        }
    }

    private void validatePagination(int pageNumber, int pageSize) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page number must not be negative");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be greater than zero");
        }
        if (pageSize > 100) {
            throw new IllegalArgumentException("Page size must not exceed 100");
        }
    }

    private DepartmentResponseDto mapDepartmentToResponseDto(Department department) {
        DepartmentResponseDto dto = modelMapper.map(department, DepartmentResponseDto.class);

        List<EmployeeResponseDto> employeeDtos = department.getEmployees().stream()
                .map(emp -> modelMapper.map(emp, EmployeeResponseDto.class))
                .collect(Collectors.toList());

        dto.setEmployees(employeeDtos);
        return dto;
    }
}