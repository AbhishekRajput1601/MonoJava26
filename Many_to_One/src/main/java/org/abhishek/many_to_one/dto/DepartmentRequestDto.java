package org.abhishek.many_to_one.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDto {

    @NotBlank(message = "Department name is required")
    @JsonProperty("department_name")
    private String departmentName;

    @NotBlank(message = "Location is required")
    private String location;

    @NotEmpty(message = "At least one employee is required")
    @Valid
    private List<EmployeeRequestDto> employees;
}
