package com.ivoyant.SpringBootFinalProject.exception;
public class DuplicateDepartmentNameException extends RuntimeException {
        public DuplicateDepartmentNameException(String message) {
            super(message);
        }
    }

