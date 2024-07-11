package com.erainfotechbd.employee.service;

import com.erainfotechbd.employee.entity.Employee;
import com.erainfotechbd.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.erainfotechbd.employee.entity.Employee;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeServiceTests {

    @Autowired
    EmployeeRepository employeeRepository;

    @ParameterizedTest
    @ValueSource(longs = {
            4552,
            3010,
            4402,
            2020

    })
    public void getEmployeeById(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);

        assertTrue(employee.isPresent(), "Failed for ID: " + id);

        employee.ifPresent(employees -> System.out.println(employee));

    }




    @CsvSource({
            "120",
            "1",
            "1001",
            "454"
    })
    @ParameterizedTest
    public void getByEmpId(Long empId){
        Employee employee=employeeRepository.findByEmpId(empId);
        assertNotNull(employee, "This employee not exits by : "+empId);
        System.out.println(employee);

    }



    @Disabled
    @ParameterizedTest
    @CsvSource({
            "5,5,10",
            "4,3,8",
            "4,4,8",
            "-1,-8,7"
    })
    public void Test(int a, int b, int expected){
        assertEquals(expected, a+b);

    }
}
