
package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Handle validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	// Handle illegal arguments, including duplicate leave requests
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {

		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	// Handle unexpected errors
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {

		Map<String, String> error = new HashMap<>();
		error.put("error", "An unexpected error occurred");

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(LeaveNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleLeaveNotFound(LeaveNotFoundException ex) {

		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateLeaveException.class)
	public ResponseEntity<Map<String, String>> handleDuplicateLeave(DuplicateLeaveException ex) {

		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

}
