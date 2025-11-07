package com.ecom.ocppfe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecom.ocppfe.entities.Employee;
import com.ecom.ocppfe.models.EmployeeModel;
import com.ecom.ocppfe.services.EmployeeService;
import com.ecom.ocppfe.services.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class EmployeeRestController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/employees")
    void createEmployee(@RequestBody EmployeeModel e) {
        Employee employee = new Employee();
        employee.setEmployeeLastName(e.getEmployeeLastName());
        employee.setEmployeePhone(e.getEmployeePhoneNumber());
        employee.setEmployeeFirstName(e.getEmployeeFirstName());
        employee.setDepartment(this.departmentService.findOneDepartment(e.getDepartmentId()));
        this.employeeService.createEmployee(employee);

    }

    @GetMapping("/employees")
    List<Employee> findAllEmployees() {
        return this.employeeService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    Employee getOneEmployee(@PathVariable long id) {
        return this.employeeService.getOneEmployee(id);
    }

    @GetMapping("/employees/paging")
    public ResponseEntity<Map<String, Object>> findAllByPaging(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        return new ResponseEntity<>(this.employeeService.findAllEmployees(page, size), HttpStatus.OK);
    }

    @DeleteMapping("/employees/delete/{id}")
    public void deleteEmployee(@PathVariable  long id) {
        this.employeeService.delete(id);
    }

    @PutMapping("/employees/update/{id}")
    public  Employee updateEmployee(@RequestBody EmployeeModel e, @PathVariable long id) {
        Employee employee = new Employee();
        employee.setEmployeeFirstName(e.getEmployeeFirstName());
        employee.setEmployeePhone(e.getEmployeePhoneNumber());
        employee.setEmployeeLastName(e.getEmployeeLastName());
        employee.setDepartment(this.departmentService.findOneDepartment(e.getDepartmentId()));

        return this.employeeService.update(employee, id);
    }

}
