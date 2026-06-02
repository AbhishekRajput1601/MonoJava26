package org.abhishek.many_to_one.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.abhishek.many_to_one.dto.DepartmentRequestDto;
import org.abhishek.many_to_one.dto.DepartmentResponseDto;
import org.abhishek.many_to_one.dto.PageResponseDto;
import org.abhishek.many_to_one.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Slf4j
@Tag(name = "Department Management", description = "APIs for managing departments and employees")
@SecurityRequirement(name = "basicAuth")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new department with employees",
            description = "Create a department along with multiple employees. Only ADMIN can perform this operation.")
    public ResponseEntity<DepartmentResponseDto> createDepartment(
            @Valid @RequestBody DepartmentRequestDto requestDto) {
        log.info("API request received to create department");
        DepartmentResponseDto response = departmentService.createDepartment(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Get all departments",
            description = "Fetch all departments with their employees")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {
        log.info("API request received to get all departments");
        List<DepartmentResponseDto> response = departmentService.getAllDepartments();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/page")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Get departments with pagination",
            description = "Fetch departments with pagination support")
    public ResponseEntity<PageResponseDto<DepartmentResponseDto>> getAllDepartmentsWithPagination(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize) {
        log.info("API request received to get departments with pagination - pageNumber: {}, pageSize: {}", pageNumber, pageSize);
        PageResponseDto<DepartmentResponseDto> response = departmentService.getAllDepartmentsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Get department by ID",
            description = "Fetch a specific department with its employees by ID")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(
            @PathVariable Long id) {
        log.info("API request received to get department by ID: {}", id);
        DepartmentResponseDto response = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update department",
            description = "Update department details and employee list. Only ADMIN can perform this operation.")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDto requestDto) {
        log.info("API request received to update department with ID: {}", id);
        DepartmentResponseDto response = departmentService.updateDepartment(id, requestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete department",
            description = "Delete a department along with all its employees. Only ADMIN can perform this operation.")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Long id) {
        log.info("API request received to delete department with ID: {}", id);
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}

