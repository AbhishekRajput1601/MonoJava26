package org.abhishek.mapping_with_swagger.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {

    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    private Integer age;

    private StudentProfileResponseDto profile;
}