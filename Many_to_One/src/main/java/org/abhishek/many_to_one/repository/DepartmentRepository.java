package org.abhishek.many_to_one.repository;

import jdk.jfr.Registered;
import org.abhishek.many_to_one.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByDepartmentName(String departmentName);
    boolean existsByDepartmentNameAndIdNot(String departmentName, Long id);

}
