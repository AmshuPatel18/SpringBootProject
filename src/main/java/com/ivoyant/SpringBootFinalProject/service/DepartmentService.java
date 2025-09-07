package com.ivoyant.SpringBootFinalProject.service;

import com.ivoyant.SpringBootFinalProject.entity.Department;
import com.ivoyant.SpringBootFinalProject.exception.DepartmentNotFoundException;
import com.ivoyant.SpringBootFinalProject.exception.DuplicateDepartmentNameException;
import com.ivoyant.SpringBootFinalProject.exception.NameBlankException;
import com.ivoyant.SpringBootFinalProject.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;

    // Create department
    public Department createDept(Department dept) {
        if (dept.getDeptName() == null || dept.getDeptName().trim().isEmpty()) {
            throw new NameBlankException("Department");
        }

        // Check for duplicate name
        departmentRepo.findByDeptName(dept.getDeptName()).ifPresent(existing -> {
            throw new DuplicateDepartmentNameException("Department already exists!");
        });

        return departmentRepo.save(dept);
    }

    // Update department
    public Department updateDept(Integer id, Department dept) {
        if (!departmentRepo.existsById(id)) {
            throw new DepartmentNotFoundException(id);
        }

        if (dept.getDeptName() == null || dept.getDeptName().trim().isEmpty()) {
            throw new NameBlankException("Department");
        }

        // Check for duplicate name excluding current dept
        departmentRepo.findByDeptName(dept.getDeptName()).ifPresent(existing -> {
            if (!existing.getDeptId().equals(id)) {
                throw new DuplicateDepartmentNameException("Department already exists!");
            }
        });

        dept.setDeptId(id);
        return departmentRepo.save(dept);
    }

    // Get single department
    public Department getDept(Integer id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    // Get all departments
    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    // Delete department
    public void deleteDept(Integer id) {
        if (!departmentRepo.existsById(id)) {
            throw new DepartmentNotFoundException(id);
        }
        departmentRepo.deleteById(id);
    }
}
