package org.abhishek.many_to_one.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDto {

    private Long id;

    @JsonProperty("department_name")
    private String departmentName;

    private String location;

    private List<EmployeeResponseDto> employees;
}
