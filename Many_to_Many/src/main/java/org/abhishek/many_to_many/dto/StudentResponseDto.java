package org.abhishek.many_to_many.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    private String email;

    private Integer age;

    private List<CourseResponseDto> courses;
}

