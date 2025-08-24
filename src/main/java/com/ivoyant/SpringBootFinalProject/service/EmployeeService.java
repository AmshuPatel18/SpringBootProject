package com.ivoyant.SpringBootFinalProject.service;

import com.ivoyant.SpringBootFinalProject.entity.Employee;
import com.ivoyant.SpringBootFinalProject.repository.DepartmentRepository;
import com.ivoyant.SpringBootFinalProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    public Employee createEmployee(Employee emp, Integer deptId) {
        // Validate dept exists
        if(!departmentRepo.existsById(deptId)) {
            throw new RuntimeException("Invalid department ID");
        }
        emp.setDeptId(deptId);
        return employeeRepo.save(emp);
    }

    public Employee updateEmployee(Integer id, Employee emp, Integer deptId) {
        if(!employeeRepo.existsById(id))
            throw new RuntimeException("Employee not found");

        if(!departmentRepo.existsById(deptId))
            throw new RuntimeException("Invalid department ID");

        emp.setEmpId(id);
        emp.setDeptId(deptId);
        return employeeRepo.save(emp);
    }

    public Employee getEmployee(Integer id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> getAllEmployees(Integer deptId) {
        if(deptId == null) {
            return employeeRepo.findAll();
        }
        return employeeRepo.findByDeptId(deptId);
    }

    public void deleteEmployee(Integer id) {
        employeeRepo.deleteById(id);
    }
}
