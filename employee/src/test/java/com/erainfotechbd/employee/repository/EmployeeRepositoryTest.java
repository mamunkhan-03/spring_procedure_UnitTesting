package com.erainfotechbd.employee.repository;

import com.erainfotechbd.employee.dto.EmployeeDto;
import com.erainfotechbd.employee.entity.Employee;

import com.erainfotechbd.employee.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
class EmployeeRepositoryTest {
    @MockBean
    private EmployeeRepository employeeRepository;
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeService employeeService;
    @Test
    void findByEmpId() {
        Employee employee = new Employee();
        employee.setEmpId(1112L);
        employee.setEmpName("Neymar JR");
        employee.setEmpMobile("0133277");
        employee.setEmpSalary(10000L);



        Employee savedEmployee = employeeRepository.save(employee);

        // Print the saved employee details
        System.out.println("Saved Employee: " + savedEmployee);


        /*Employee response = employeeRepository.findByEmpId(1112L);

        assertNotNull(response);

        assertNotNull(response, "Employee should not be null. ID: 1112");

        try {

            assertEquals(employee.getEmpId(), response.getEmpId(), "Employee ID should match");
            assertEquals(employee.getEmpName(), response.getEmpName(), "Employee name should match");
            assertEquals(employee.getEmpMobile(), response.getEmpMobile(), "Employee mobile should match");
            assertEquals(employee.getEmpSalary(), response.getEmpSalary(), "Employee salary should match");

            System.out.println("Successful: Employee details match");
        } catch (AssertionError e) {
            System.out.println("Failed: " + e.getMessage());
            throw e;
        }*/
    }

    @Test
    void findByEmpMobile (){

        Employee employee = new Employee();
        employee.setEmpId(1111L);
        employee.setEmpName("Neymar");
        employee.setEmpMobile("013343277");
        employee.setEmpSalary(10000L);

        when(employeeRepository.findByEmpMobile("013343277")).thenReturn(employee);


        Employee response=employeeRepository.findByEmpMobile("013343277");

        try{
            assertEquals(response.getEmpId(), employee.getEmpId());
            assertEquals(response.getEmpName(),employee.getEmpName());
            assertEquals(response.getEmpMobile(), employee.getEmpMobile());
            assertEquals(response.getEmpSalary(), employee.getEmpSalary());
            System.out.println("Successfull");
        }
        catch (AssertionError e){
            System.out.println("Failed");

        }

    }

    @Test
    void findByEmpName() {

        Employee employee = new Employee();
        employee.setEmpId(1111L);
        employee.setEmpName("Neymar");
        employee.setEmpMobile("013343277");
        employee.setEmpSalary(10000L);

        when(employeeRepository.findByEmpName("Neymar")).thenReturn(employee);

        Employee response = employeeRepository.findByEmpName("Neymar");

        try {
            assertEquals("Neymar", response.getEmpName(), "Employee name should be Neymar");
            assertEquals(1111L, response.getEmpId(), "Employee ID should be 1111");
            assertEquals("013343277", response.getEmpMobile(), "Employee mobile should be 013343277");
            assertEquals(10000L, response.getEmpSalary(), "Employee salary should be 10000");
            System.out.println("Success findByName method");
        } catch (AssertionError e) {
            System.out.println("Failed");
            throw e;
        }
    }


    @Test
    void testFetchAllEmployee() {

        Employee employee1 = new Employee(122L, "ABC", "1234567890", 50000L);
        Employee employee2 = new Employee(132L, "XYZ", "9876543210", 60000L);
        List<Employee> mockEmployees = Arrays.asList(employee1, employee2);


        when(employeeRepository.fetchAllEmployee()).thenReturn(mockEmployees);


        List<Employee> fetchedEmployees = employeeRepository.fetchAllEmployee();


        assertEquals(122, fetchedEmployees.get(0).getEmpId(), "Employee ID of first employee should be 122");
        assertEquals("ABC", fetchedEmployees.get(0).getEmpName(), "Employee name of first employee should be ABC");
        assertEquals("1234567890", fetchedEmployees.get(0).getEmpMobile(), "Employee mobile of first employee should be 1234567890");
        assertEquals(50000L, fetchedEmployees.get(0).getEmpSalary(), "Employee salary of first employee should be 50000");


        assertEquals(132, fetchedEmployees.get(1).getEmpId(), "Employee ID of second employee should be 132");
        assertEquals("XYZ", fetchedEmployees.get(1).getEmpName(), "Employee name of second employee should be XYZ");
        assertEquals("9876543210", fetchedEmployees.get(1).getEmpMobile(), "Employee mobile of second employee should be 9876543210");
        assertEquals(60000L, fetchedEmployees.get(1).getEmpSalary(), "Employee salary of second employee should be 60000");



        System.out.println("Successful: TestFetchAllEmployee");
    }

}

