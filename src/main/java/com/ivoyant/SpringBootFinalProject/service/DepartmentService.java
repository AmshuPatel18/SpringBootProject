package com.ivoyant.SpringBootFinalProject.service;

import com.ivoyant.SpringBootFinalProject.entity.Department;
import com.ivoyant.SpringBootFinalProject.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepo;

    public Department createDept(Department dept) {
        return departmentRepo.save(dept);
    }

    public Department updateDept(Integer id, Department dept) {
        if(!departmentRepo.existsById(id))
            throw new RuntimeException("Department not found");
        dept.setDeptId(id);
        return departmentRepo.save(dept);
    }

    public Department getDept(Integer id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    public void deleteDept(Integer id) {
        departmentRepo.deleteById(id);
    }
}
