package com.ivoyant.SpringBootFinalProject.service;

import com.ivoyant.SpringBootFinalProject.entity.Employee;
import com.ivoyant.SpringBootFinalProject.exception.*;
import com.ivoyant.SpringBootFinalProject.repository.DepartmentRepository;
import com.ivoyant.SpringBootFinalProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    // Create Employee
    public Employee createEmployee(Employee emp, Integer deptId) {
        // Name cannot be blank
        if (emp.getEmpName() == null || emp.getEmpName().trim().isEmpty()) {
            throw new NameBlankException("Employee");
        }

        // Department must exist
        if (!departmentRepo.existsById(deptId)) {
            throw new DepartmentNotFoundException(deptId);
        }

        // Duplicate Employee ID
        if (employeeRepo.existsById(emp.getEmpId())) {
            throw new DuplicateEmployeeIdException(emp.getEmpId());
        }

        // Email validation
        if (!emp.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidInputException("Email " + emp.getEmail() + " is not valid.");
        }

        // Phone validation (10 digits)
        if (!emp.getPhone().matches("\\d{10}")) {
            throw new InvalidInputException("Phone number must be 10 digits.");
        }

        emp.setDeptId(deptId);
        return employeeRepo.save(emp);
    }

    // Update Employee
    public Employee updateEmployee(Integer id, Employee emp, Integer deptId) {
        if (!employeeRepo.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }


        if (!departmentRepo.existsById(deptId)) {
            throw new DepartmentNotFoundException(deptId);
        }

        if (emp.getEmpName() == null || emp.getEmpName().trim().isEmpty()) {
            throw new NameBlankException("Employee");
        }

        if (!emp.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidInputException("Email " + emp.getEmail() + " is not valid.");
        }

        if (!emp.getPhone().matches("\\d{10}")) {
            throw new InvalidInputException("Phone number must be 10 digits.");
        }

        // Prevent changing ID or department
        emp.setEmpId(id);
        emp.setDeptId(deptId);

        return employeeRepo.save(emp);
    }

    // 🔹 Get Employee by ID
    public Employee getEmployee(Integer id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // 🔹 Get All Employees (optional department filter)
    public List<Employee> getAllEmployees(Integer deptId) {
        if (deptId == null) {
            return employeeRepo.findAll();
        }

        if (!departmentRepo.existsById(deptId)) {
            throw new DepartmentNotFoundException(deptId);
        }

        return employeeRepo.findByDeptId(deptId);
    }

    // 🔹 Delete Employee
    public void deleteEmployee(Integer id) {
        if (!employeeRepo.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepo.deleteById(id);
    }
}
