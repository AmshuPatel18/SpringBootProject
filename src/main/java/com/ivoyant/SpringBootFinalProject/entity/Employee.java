package com.ivoyant.SpringBootFinalProject.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Table("employee")
public class Employee {

    @PrimaryKey
    private UUID employeeId;

    @NotBlank(message = "Employee name is required")
    private String employeeName;

    @Positive(message = "Salary must be positive")
    private BigDecimal salary;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    private UUID departmentId;

    // Constructors
    public Employee() {
        this.employeeId = UUID.randomUUID();
    }

    public Employee(String employeeName, BigDecimal salary, String email, String phoneNumber, UUID departmentId) {
        this.employeeId = UUID.randomUUID();
        this.employeeName = employeeName;
        this.salary = salary;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
    }

    // Getters and Setters
    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UUID getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(UUID departmentId) {
        this.departmentId = departmentId;
    }
}
