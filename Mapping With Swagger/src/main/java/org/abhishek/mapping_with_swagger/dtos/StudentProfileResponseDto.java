package org.abhishek.mapping_with_swagger.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileResponseDto {

    private Long id;
    private String email;
    private String phone;
    private String city;
}