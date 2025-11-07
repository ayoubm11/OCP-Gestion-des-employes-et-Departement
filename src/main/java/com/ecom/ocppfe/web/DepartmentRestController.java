package com.ecom.ocppfe.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ecom.ocppfe.entities.Department;
import com.ecom.ocppfe.models.DepartmentModel;
import com.ecom.ocppfe.services.DepartmentService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class DepartmentRestController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/departments")
    void createDepartment(@RequestBody DepartmentModel model) {
        Department department =  new Department();
        department.setDepartmentName(model.getDepartmentName());
        this.departmentService.createDepartment(department);
    }

    @GetMapping("/departments")
    List<Department> Departments() {
        return  this.departmentService.findDepartments();
    }

    @GetMapping("/departments/{id}")
    Department  getOneDepartment(@PathVariable long id) {
        return this.departmentService.findOneDepartment(id);
    }
}
