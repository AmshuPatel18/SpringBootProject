package com.ivoyant.SpringBootFinalProject.service;

import com.ivoyant.SpringBootFinalProject.entity.Department;
import com.ivoyant.SpringBootFinalProject.exception.DepartmentNotFoundException;
import com.ivoyant.SpringBootFinalProject.exception.NameBlankException;
import com.ivoyant.SpringBootFinalProject.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;

    // 🔹 Create Department
    public Department createDept(Department dept) {
        if (dept.getDeptName() == null || dept.getDeptName().trim().isEmpty()) {
            throw new NameBlankException("Department");
        }
        return departmentRepo.save(dept);
    }

    // 🔹 Update Department
    public Department updateDept(Integer id, Department dept) {
        if (!departmentRepo.existsById(id)) {
            throw new DepartmentNotFoundException(id);
        }

        if (dept.getDeptName() == null || dept.getDeptName().trim().isEmpty()) {
            throw new NameBlankException("Department");
        }

        dept.setDeptId(id); // Prevent ID change
        return departmentRepo.save(dept);
    }

    // 🔹 Get Department by ID
    public Department getDept(Integer id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    // 🔹 Get All Departments
    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    // 🔹 Delete Department
    public void deleteDept(Integer id) {
        if (!departmentRepo.existsById(id)) {
            throw new DepartmentNotFoundException(id);
        }
        departmentRepo.deleteById(id);
    }
}
