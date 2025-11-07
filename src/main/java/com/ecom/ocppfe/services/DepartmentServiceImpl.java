package com.ecom.ocppfe.services;


import org.springframework.stereotype.Service;
import com.ecom.ocppfe.entities.Department;
import com.ecom.ocppfe.repositories.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    final private DepartmentRepository departmentRepository ;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository =  departmentRepository;
    }
    @Override
    public void createDepartment(Department d) {
        this.departmentRepository.save(d);
    }

    @Override
    public List<Department> findDepartments() {
        return this.departmentRepository.findAll();
    }

    @Override
    public Department findOneDepartment(long id) {
        return this.departmentRepository.findById(id).get();
    }
}
