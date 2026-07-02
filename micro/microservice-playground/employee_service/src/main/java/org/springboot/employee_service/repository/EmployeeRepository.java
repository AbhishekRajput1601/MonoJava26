package org.springboot.employee_service.repository;

import java.util.List;

import org.springboot.employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentId(Long departmentId);
}
