package com.zikozee.springboot.cruddemo.repo;

import com.zikozee.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // that's it  ... no need to write any code LOL!
    List<Employee> findAllByFirstNameAndLastName(String firstName, String lastName);
}
