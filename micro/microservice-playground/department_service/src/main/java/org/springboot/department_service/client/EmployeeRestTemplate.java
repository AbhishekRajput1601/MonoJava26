package org.springboot.department_service.client;

import lombok.RequiredArgsConstructor;
import org.springboot.department_service.dto.EmployeeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.stylesheets.LinkStyle;

import java.lang.reflect.Type;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeRestTemplate {
    private final RestTemplate restTemplate;

    @Value("${employee.service.url}")
    private String employeeServiceUrl;

    public List<EmployeeResponse> getEmployeeByDepartmentId(Long departmentId) {
        String url = employeeServiceUrl + "/employees/department/" + departmentId;

        ResponseEntity<List<EmployeeResponse>> response = restTemplate.exchange(url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<EmployeeResponse>>() {

        });

        return response.getBody();
    }

}
