package com.ivoyant.SpringBootFinalProject.controller;

import com.ivoyant.SpringBootFinalProject.entity.Employee;
import com.ivoyant.SpringBootFinalProject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    public Employee create(
            @RequestHeader("departmentId") Integer deptId,
            @RequestBody @Valid Employee emp) {
        return employeeService.createEmployee(emp, deptId);
    }


    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Integer id,
            @RequestHeader("departmentId") Integer deptId,
            @RequestBody @Valid Employee emp) {
        return employeeService.updateEmployee(id, emp, deptId);
    }


    @GetMapping("/{id}")
    public Employee get(@PathVariable Integer id) {
        return employeeService.getEmployee(id);
    }

    // get all employees ( department filter via query param)
    @GetMapping
    public List<Employee> getAll(@RequestParam(value = "departmentId", required = false) Integer deptId) {
        return employeeService.getAllEmployees(deptId);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }
}
