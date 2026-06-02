package org.abhishek.many_to_many.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {
    private Long id;

    @JsonProperty("course_name")
    private String courseName;

    @JsonProperty("course_code")
    private String courseCode;

    private Double fees;
}

