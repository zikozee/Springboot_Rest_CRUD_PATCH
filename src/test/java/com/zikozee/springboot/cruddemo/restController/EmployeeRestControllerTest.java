package com.zikozee.springboot.cruddemo.restController;

import com.zikozee.springboot.cruddemo.entity.Employee;
import com.zikozee.springboot.cruddemo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmployeeRestControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeRestController controller;

    MockMvc mockMvc;

    Employee employee;
    Employee anotherEmployee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1);
        anotherEmployee = new Employee();
        anotherEmployee.setId(2);

        mockMvc  = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAll() throws Exception{
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployee() throws Exception {
        when(employeeService.findById(anyInt())).thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk());
    }

    @Test
    void addEmployee() throws Exception {

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\" : \"Mercy\", \"lastName\": \"Olime\",\"email\": \"linda.eromosei@gmail.com\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Mercy"))
                .andExpect(jsonPath("$.lastName").value("Olime"))
                .andExpect(jsonPath("$.email").value("linda.eromosei@gmail.com"));

    }

    @Test
    void updateEmployee() throws Exception {
        mockMvc.perform(put("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\" : \"Mercy\", \"lastName\": \"Olime\",\"email\": \"linda.eromosei@gmail.com\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Mercy"))
                .andExpect(jsonPath("$.lastName").value("Olime"))
                .andExpect(jsonPath("$.email").value("linda.eromosei@gmail.com"));

    }

    @Test
    void patchEmployee() throws Exception {
        when(employeeService.findById(anyInt())).thenReturn(employee);

        mockMvc.perform(patch("/employees/1?email=abc@hbng.com"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployee() throws Exception {
        when(employeeService.findById(anyInt())).thenReturn(employee);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk());
    }

    @Test
    void searchByFirstAndLastName() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(anotherEmployee);
        when(employeeService.findByFirstAndAndLast(anyString(),anyString())).thenReturn(employees);

        mockMvc.perform(get("/employees/Bola/Noel"))
                .andExpect(status().isOk());
    }
}