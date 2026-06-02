package org.abhishek.many_to_one.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Map<String, String> messages;
}
