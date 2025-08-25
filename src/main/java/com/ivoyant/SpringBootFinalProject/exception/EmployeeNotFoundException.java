package com.ivoyant.SpringBootFinalProject.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Integer id) {
        super("Employee with ID " + id + " not found.");
    }
}
