package com.ecom.ocppfe.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ecom.ocppfe.entities.Employee;
import com.ecom.ocppfe.repositories.EmployeeRepository;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    final private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public void createEmployee(Employee e) {
        this.employeeRepository.save(e);
    }

    @Override
    public List<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee getOneEmployee(long id) {
        return this.employeeRepository.findById(id).get();
    }

    @Override
    public Map<String, Object> findAllEmployees(int page, int size) {
        try {
            List<Employee> employeeList =  new ArrayList<>();
            Pageable paging =  PageRequest.of(page, size);
            Page<Employee> pageEmployee =  this.employeeRepository.findAll(paging);
            employeeList =  pageEmployee.getContent();

            Map<String, Object> employees = new HashMap<>();

            employees.put("employees", employeeList);
            employees.put("pageCurrent", pageEmployee.getNumber());
            employees.put("totalItems", pageEmployee.getTotalElements());
            employees.put("totalPage", pageEmployee.getTotalPages());

            return  employees;

        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Employee update(Employee e, long id) {

        Employee employee =  this.getOneEmployee(id);

        employee.setEmployeePhone(e.getEmployeePhone());
        employee.setEmployeeLastName(e.getEmployeeLastName());
        employee.setDepartment(e.getDepartment());
        employee.setEmployeeFirstName(e.getEmployeeFirstName());

        return this.employeeRepository.save(employee);

    }


}
