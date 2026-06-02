package org.abhishek.many_to_many.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequestDto {

    @JsonProperty("course_name")
    @NotBlank
    private String courseName;

    @JsonProperty("course_code")
    @NotBlank
    private String courseCode;

    @NotNull
    @Min(value = 1)
    private Double fees;
}

