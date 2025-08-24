package com.ivoyant.SpringBootFinalProject.controller;

import com.ivoyant.SpringBootFinalProject.entity.Department;
import com.ivoyant.SpringBootFinalProject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService deptService;

    @PostMapping
    public Department create(@RequestBody @Valid Department dept) {
        return deptService.createDept(dept);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Integer id, @RequestBody @Valid Department dept) {
        return deptService.updateDept(id, dept);
    }

    @GetMapping("/{id}")
    public Department get(@PathVariable Integer id) {
        return deptService.getDept(id);
    }

    @GetMapping
    public List<Department> getAll() {
        return deptService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        deptService.deleteDept(id);
    }
}
