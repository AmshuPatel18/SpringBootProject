package com.ivoyant.SpringBootFinalProject.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;

import javax.validation.constraints.NotBlank;



@Table("department")
public class Department {

    @PrimaryKey("dept_id")   // maps directly to Cassandra column
    private Integer deptId;

    @Column("dept_name")
    @NotBlank(message = "Department name cannot be empty")
    private String deptName;


    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
