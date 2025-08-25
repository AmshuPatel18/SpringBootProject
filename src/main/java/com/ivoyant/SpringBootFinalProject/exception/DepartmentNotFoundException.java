package com.ivoyant.SpringBootFinalProject.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(Integer id) {
        super("Department with ID " + id + " does not exist.");
    }
}
