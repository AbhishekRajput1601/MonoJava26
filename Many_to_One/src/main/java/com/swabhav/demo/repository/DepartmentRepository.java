package com.swabhav.demo.repository;

import com.swabhav.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByDepartmentName(String departmentName);
    boolean existsByDepartmentNameAndIdNot(String departmentName, Long id);

}
