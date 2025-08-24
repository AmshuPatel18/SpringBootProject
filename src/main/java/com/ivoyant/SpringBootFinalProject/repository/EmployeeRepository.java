package com.ivoyant.SpringBootFinalProject.repository;

import com.ivoyant.SpringBootFinalProject.entity.Employee;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface EmployeeRepository extends CassandraRepository<Employee, Integer> {
    List<Employee> findByDeptId(Integer deptId);
}