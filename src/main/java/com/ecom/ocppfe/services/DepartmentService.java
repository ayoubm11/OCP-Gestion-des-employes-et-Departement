package com.ecom.ocppfe.services;

import com.ecom.ocppfe.entities.Department;

import java.util.List;

public interface DepartmentService {

    void createDepartment(Department d);

    List<Department> findDepartments();

    Department findOneDepartment(long id);
}
