package org.abhishek.studentmanager.exceptions;

import java.time.Instant;
import java.util.List;

import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex, WebRequest request) {
		log.warn("Student not found: {}", ex.getMessage());
		return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request, List.<FieldErrorDetail>of());
	}


	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
		log.warn("Invalid value for parameter '{}': {}", ex.getName(), ex.getValue());
		String message = "Invalid value for parameter '" + ex.getName() + "'";
		return buildErrorResponse(HttpStatus.BAD_REQUEST, message, request, List.<FieldErrorDetail>of());
	}


	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
		log.warn("Bad request: {}", ex.getMessage());
		return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request, List.<FieldErrorDetail>of());
	}


	private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message, WebRequest request,
			List<FieldErrorDetail> fieldErrors) {
		String path = request instanceof ServletWebRequest servletWebRequest
				? servletWebRequest.getRequest().getRequestURI()
				: "N/A";

		ErrorResponse errorResponse = new ErrorResponse(
				Instant.now(),
				status.value(),
				status.getReasonPhrase(),
				message,
				path,
				fieldErrors);

		return ResponseEntity.status(status).body(errorResponse);
	}
}



