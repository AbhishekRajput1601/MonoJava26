package org.springboot.department_service.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentWithEmployeesResponse {

    private Long departmentId;
    private String departmentName;
    private List<EmployeeResponse> employees;
}
