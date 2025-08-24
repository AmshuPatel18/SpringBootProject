package com.ivoyant.SpringBootFinalProject.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;

import javax.validation.constraints.*;

import java.math.BigDecimal;


@Table("employee")
public class Employee {

    @PrimaryKey("emp_id")   // ✅ matches Cassandra column exactly
    private Integer empId;

    @Column("emp_name")
    @NotBlank(message = "Employee name cannot be empty")
    private String empName;

    @Column("salary")
    @DecimalMin(value = "0.0", message = "Salary must be positive")
    private BigDecimal salary;

    @Column("email")
    @Email(message = "Invalid email format")
    private String email;

    @Column("phone")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    @Column("dept_id")
    private Integer deptId;

    // Getters & Setters
    public Integer getEmpId() {
        return empId;
    }
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
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

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
