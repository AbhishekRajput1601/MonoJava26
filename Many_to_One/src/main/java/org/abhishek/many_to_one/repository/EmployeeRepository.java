package org.abhishek.many_to_one.repository;

import org.abhishek.many_to_one.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}
