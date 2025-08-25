package com.ivoyant.SpringBootFinalProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔹 Common response builder
    private ResponseEntity<Map<String, Object>> buildResponse(Object message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }

    // 🔹 Handle validation errors (@Valid, @NotBlank, @Email, @Pattern etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        // Collect all field errors
        Map<String, String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, replacement) -> existing // in case of duplicate field errors
                ));
        return buildResponse(errors, HttpStatus.BAD_REQUEST);
    }

    // custom "not found" exceptions
    @ExceptionHandler({DepartmentNotFoundException.class, EmployeeNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleNotFound(RuntimeException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 🔹 Handle duplicate employee ID
    @ExceptionHandler(DuplicateEmployeeIdException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateEmployee(DuplicateEmployeeIdException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // 🔹 Handle invalid input or blank names
    @ExceptionHandler({InvalidInputException.class, NameBlankException.class})
    public ResponseEntity<Map<String, Object>> handleBadRequest(RuntimeException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 🔹 Catch-all for unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        return buildResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
