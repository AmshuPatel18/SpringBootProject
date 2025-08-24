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
    private EmployeeService empService;

    @PostMapping
    public Employee create(@RequestHeader("deptId") Integer deptId,
                           @RequestBody @Valid Employee emp) {
        return empService.createEmployee(emp, deptId);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Integer id,
                           @RequestHeader("deptId") Integer deptId,
                           @RequestBody @Valid Employee emp) {
        return empService.updateEmployee(id, emp, deptId);
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable Integer id) {
        return empService.getEmployee(id);
    }

    @GetMapping
    public List<Employee> getAll(@RequestParam(required = false) Integer deptId) {
        return empService.getAllEmployees(deptId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        empService.deleteEmployee(id);
    }
}
