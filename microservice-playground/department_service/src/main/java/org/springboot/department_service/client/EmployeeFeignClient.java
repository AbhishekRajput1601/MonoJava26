package org.springboot.department_service.client;

import org.springboot.department_service.dto.EmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "employee-service", url = "${employee.service.url}")
public interface EmployeeFeignClient {
    @GetMapping("/employees/department/{departmentId}")
    List<EmployeeResponse> getEmployeesByDepartmentId(@PathVariable("departmentId") Long departmentId);
}
