package com.erainfotechbd.employee.service;

import com.erainfotechbd.employee.dto.EmployeeDto;
import com.erainfotechbd.employee.dto.ResponseEmpDto;
import com.erainfotechbd.employee.entity.Employee;
import com.erainfotechbd.employee.repository.EmployeeRepository;
import com.erainfotechbd.employee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class EmployeeServiceTestsUsingMockito {

    @Autowired
    EmployeeService employeeService;

    @MockBean
    EmployeeRepository employeeRepository;


    @BeforeEach
    void setup(){
        Employee employee =new Employee();
        employee.setId(3952L);
        employee.setEmpId(121L);
        employee.setEmpName("Eusha");
        employee.setEmpMobile("018020502326");
        employee.setEmpSalary(10000L);
        employeeRepository.save(employee);

        when(employeeRepository.findByEmpId(121L)).thenReturn(employee);
        when(employeeRepository.findByEmpName("Eusha")).thenReturn(employee);
        when(employeeRepository.findByEmpMobile("018020502326")).thenReturn(employee);
        when (employeeRepository.findById(3952L)).thenReturn(Optional.of(employee));

        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(employee);
        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        doNothing().when(employeeRepository).delete(employee);

       // when(employeeRepository.delete(any(Employee.class))).thenReturn(null);



        //write here to update test


    }

    @Test
    public void getEmployeeByEmpId(){
        String name="Eusha";
        EmployeeDto employeeDto= employeeService.getByEmpId(121L);
        assertEquals(name, employeeDto.getEmpName());

    }

    @Test
    public void getEmployeeByEmpName(){
       // when(employeeRepository.findByEmpName("Eusha")).thenReturn(new Employee());
        EmployeeDto response= employeeService.getByEmpName("Eusha");
        assertEquals("Eusha", response.getEmpName());
        System.out.println(response.getEmpName());
    }



    @Test
    public void getEmployeeByEmpMobile(){
        EmployeeDto response=employeeService.getByEmpMobile("018020502326");
        assertEquals(121,response.getEmpId() );
        assertEquals("Eusha",response.getEmpName() );
        assertEquals("018020502326",response.getEmpMobile());
        assertEquals(10000,response.getEmpSalary());

        System.out.println(response);

    }

    @Test
    public void createEmployee(){


    }

    @Test
    public void getEmployeeById(){
       EmployeeDto response= employeeService.getEmployeeById(3952L);
       assertEquals(3952, response.getId());
    }

    @Test
    public void getAllEmployee(){

        List<EmployeeDto> employees=employeeService.getAllEmployee();
        //List<Employee> employees= employeeRepository.findAll();
        assertNotNull(employees);
        assertEquals(employees.get(0).getId(), 3952);
        System.out.println("successful");

    }

    @Test
    public void updateEmployee(){
        EmployeeDto updatedEmployeeDto = new EmployeeDto();
        updatedEmployeeDto.setEmpId(121L); // Assuming this is the ID being updated
        updatedEmployeeDto.setEmpName("Updated Name");
        updatedEmployeeDto.setEmpMobile("018020502326");
        updatedEmployeeDto.setEmpSalary(15000L);

        ResponseEmpDto response = employeeService.updateEmployee(updatedEmployeeDto, 3952L);


        assertNotNull(response);
        assertEquals(2, response.getResponseCode());
        assertEquals("Updated successfully", response.getResponseMessage());


        Employee updatedEmployee = employeeRepository.findById(3952L).orElse(null);
        assertNotNull(updatedEmployee);
        assertEquals("Updated Name", updatedEmployee.getEmpName());
        assertEquals(15000L, updatedEmployee.getEmpSalary());
    }

    @Test
    public void deleteEmployeeById() {
        // Call the service method
        ResponseEmpDto response = employeeService.deleteEmployeeById(3952L);

        // Assertions
        assertNotNull(response);
        assertEquals(3, response.getResponseCode());
        assertEquals("Deleted successfully", response.getResponseMessage());

        // Verify repository method calls
        verify(employeeRepository, times(1)).findById(3952L);
        verify(employeeRepository, times(1)).delete(any(Employee.class));
    }





































 /*   @InjectMocks
    EmployeeServiceImpl employeeService;
    @Autowired
    EmployeeService service;

    @Mock
    EmployeeRepository employeeRepository;

    @BeforeEach
 void setup(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void findEmployeeByName() {

        when(employeeRepository.findByEmpName(ArgumentMatchers.anyString())).thenReturn(
                Employee.builder()
                        .empId(1234L) // or any appropriate ID
                        .empName("Eusha")
                        .empMobile("1234567890")
                        .empSalary(50000L)

                        .build()
        );
        EmployeeDto employee = employeeService.getByEmpName("Eusha");
        Assertions.assertNotNull(employee);

//        EmployeeDto response=employeeService.getByEmpMobile("1234567890");
//        Assertions.assertNotNull(response);

    }*/

}
