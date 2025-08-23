package com.ivoyant.SpringBootFinalProject.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("department")
public class Department {

    @PrimaryKey
    private UUID departmentId;  // UUID for primary key

    private String departmentName;

    // Constructors
    public Department() {
        this.departmentId = UUID.randomUUID(); // auto-generate ID
    }

    public Department(String departmentName) {
        this.departmentId = UUID.randomUUID();
        this.departmentName = departmentName;
    }

    // Getters and Setters
    public UUID getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(UUID departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
