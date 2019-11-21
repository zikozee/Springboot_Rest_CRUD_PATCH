package com.zikozee.springboot.cruddemo.service;

import com.zikozee.springboot.cruddemo.repo.EmployeeRepository;
import com.zikozee.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee;
        if(result.isPresent()){
            theEmployee = result.get();
        }else{
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }
        
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }

    @Override
    public Employee patch(Employee employee, String email) {
        employee.setEmail(email);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findByFirstAndAndLast(String firstName, String lastName) {
        return employeeRepository.findAllByFirstNameAndLastName(firstName,lastName);
    }
}
