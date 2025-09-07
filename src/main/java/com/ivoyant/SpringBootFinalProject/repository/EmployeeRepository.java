package com.ivoyant.SpringBootFinalProject.repository;

import com.ivoyant.SpringBootFinalProject.entity.Employee;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CassandraRepository<Employee, Integer> {


    @Query("SELECT * FROM employee WHERE dept_id = ?0 ALLOW FILTERING")
    List<Employee> findByDeptId(Integer deptId);
}
