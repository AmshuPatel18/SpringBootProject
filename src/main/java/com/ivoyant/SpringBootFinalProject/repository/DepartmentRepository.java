package com.ivoyant.SpringBootFinalProject.repository;

import com.ivoyant.SpringBootFinalProject.entity.Department;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface DepartmentRepository extends CassandraRepository<Department, Integer> {
    Optional<Department> findByDeptName(String deptName);
}
