package org.springboot.department_service.repository;

import org.springboot.department_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository
        extends JpaRepository<Department, Long> {
}
