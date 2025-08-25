package com.ivoyant.SpringBootFinalProject.exception;

public class DuplicateEmployeeIdException extends RuntimeException {
    public DuplicateEmployeeIdException(Integer id) {
        super("Employee ID " + id + " already exists.");
    }
}
