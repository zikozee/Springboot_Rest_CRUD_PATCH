package com.zikozee.springboot.cruddemo.service;

import com.zikozee.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    void save(Employee theEmployee);

    void deleteById(int theId);

    Employee patch(Employee employee, String email);

    List<Employee> findByFirstAndAndLast(String firstName, String lastName);
}
