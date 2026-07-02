package org.springboot.department_service.client;

import org.springboot.department_service.dto.EmployeeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class EmployeeRestTemplate {
    // private final RestTemplate restTemplate;
    private final WebClient webClient;

    public EmployeeRestTemplate(WebClient.Builder webClientBuilder,
            @Value("${employee.service.url}") String employeeServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(employeeServiceUrl).build();
    }

    // @Value("${employee.service.url}")
    // private String employeeServiceUrl;

    public List<EmployeeResponse> getEmployeeByDepartmentId(Long departmentId) {
        // String url = employeeServiceUrl + "/employees/department/" + departmentId;

        // ResponseEntity<List<EmployeeResponse>> response = restTemplate.exchange(url,
        // HttpMethod.GET, null,
        // new ParameterizedTypeReference<List<EmployeeResponse>>() {
        //
        // });

        // return response.getBody();

        return webClient.get().uri("/employees/department/{departmentId}", departmentId).retrieve()
                .bodyToFlux(EmployeeResponse.class).collectList().block();
    }

}
