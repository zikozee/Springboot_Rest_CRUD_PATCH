package com.zikozee.springboot.cruddemo.restController;

import com.zikozee.springboot.cruddemo.entity.Employee;
import com.zikozee.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){

        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){ //RequestBody >>>request sent from postman

        //also just in case they pass an id in JSON ... set id to 0
        // this is to force  a save of new item  ... instead of update

        theEmployee.setId(0);

        employeeService.save(theEmployee);

        return theEmployee;
    }

    // add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        //RequestBody >>>request sent from postman
        // Response contains updated info (echo)

        employeeService.save(theEmployee);

        return theEmployee;
    }
    // add mapping for Patch /employees - update existing employee
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestParam("email") String email){
        //RequestBody >>>request sent from postman
        // Response contains updated info (echo)
        Employee employee  = employeeService.findById(employeeId);

        return employeeService.patch(employee, email);
    }


    // add mapping for DELETE /employees/{employeeId} - delete employee

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee tempEmployee = employeeService.findById(employeeId);

        // throw exception if null

        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }

    @GetMapping("/employees/{firstName}/{lastName}")
    public List<Employee> SearchByFirstAndLastName(@PathVariable String firstName, @PathVariable String lastName){
        List<Employee> employees = employeeService.findByFirstAndAndLast(firstName, lastName);

        if(employees.size() == 0){
            throw new RuntimeException("no first Name as: " + firstName + " and lastName as: " + lastName);
        }
        return employees;
    }


}


















