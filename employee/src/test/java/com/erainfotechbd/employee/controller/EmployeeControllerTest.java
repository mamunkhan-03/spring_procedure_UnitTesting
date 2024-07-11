package com.erainfotechbd.employee.controller;

import com.erainfotechbd.employee.dto.EmployeeDto;
import com.erainfotechbd.employee.dto.ResponseEmpDto;
import com.erainfotechbd.employee.entity.Employee;
import com.erainfotechbd.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;



    Employee employee =new Employee();
    ResponseEmpDto responseEmpDto=new ResponseEmpDto();
    EmployeeDto employeeDto=new EmployeeDto();
    EmployeeDto employeeDto1 =new EmployeeDto();
    @BeforeEach
    void setup(){

        responseEmpDto.setResponseCode(1);
        responseEmpDto.setResponseMessage("Successfully");

        employee.setId(3952L);
        employee.setEmpId(121L);
        employee.setEmpName("Eusha");
        employee.setEmpMobile("018020502326");
        employee.setEmpSalary(10000L);


        employeeDto.setId(3952L);
        employeeDto.setEmpId(121L);
        employeeDto.setEmpName("Eusha");
        employeeDto.setEmpMobile("018020502326");
        employeeDto.setEmpSalary(10000L);
        employeeDto.getEmpId();


        employeeDto1.setId(3952L);
        employeeDto1.setEmpId(121L);
        employeeDto1.setEmpName("mamun");
        employeeDto1.setEmpMobile("018020502326");
        employeeDto1.setEmpSalary(10000L);
        employeeDto1.getEmpId();

        when(employeeService.getByEmpId(ArgumentMatchers.anyLong())).thenReturn(employeeDto);
        when(employeeService.getByEmpName(ArgumentMatchers.anyString())).thenReturn(employeeDto);
        when(employeeService.getByEmpMobile(ArgumentMatchers.anyString())).thenReturn(employeeDto);

       //when(employeeService.createEmployee(ArgumentMatchers.any(EmployeeDto.class))).thenReturn(ArgumentMatchers.any(ResponseEmpDto.class));

        when(employeeService.createEmployee(employeeDto)).thenReturn(responseEmpDto);

        List<EmployeeDto> getAllResponses=new ArrayList<>();
        getAllResponses.add(employeeDto);
        getAllResponses.add(employeeDto1);
        when(employeeService.getAllEmployee()).thenReturn(getAllResponses);

        when(employeeService.getEmployeeById(3952L)).thenReturn(employeeDto);

        when(employeeService.updateEmployee(employeeDto1,employee.getId())).thenReturn(responseEmpDto);

        when(employeeService.deleteEmployeeById(employee.getId())).thenReturn(responseEmpDto);



    }

    @Test
    void findByEmpId() throws Exception {
                 Long empId=employee.getEmpId();
                 mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/byId/{empId}",empId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.empId").value(empId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empName").value("Eusha"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empMobile").value("018020502326"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empSalary").value(10000));
    }

    @Test
    void findByEmpMobile()throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/byId/{empName}", employee.getEmpId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.empId").value(employee.getEmpId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empName").value(employee.getEmpName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empMobile").value(employee.getEmpMobile()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empSalary").value(employee.getEmpSalary()))
                .andReturn();

        // Extracting and printing the response content
        String responseContent = result.getResponse().getContentAsString();
        System.out.println("API Response:");
        System.out.println(responseContent);

    }

    @Test
    void findByEmpName()throws Exception {
      MvcResult result=  mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/byId/{empSalary}", employee.getEmpSalary())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.empId").value(employee.getEmpId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empName").value(employee.getEmpName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empMobile").value(employee.getEmpMobile()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empSalary").value(employee.getEmpSalary()))
               .andReturn();

        System.out.println(result.getResponse().getContentAsString());


    }

    @Test
    public void createEmployeeTest() throws Exception{

        String employeeDtoJson = objectMapper.writeValueAsString(employeeDto);

        MvcResult result=  mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON).content(employeeDtoJson))
                         .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void getAllEmployee() throws Exception{

        MvcResult result=  mockMvc.perform(MockMvcRequestBuilders.get("/api/employees").contentType(MediaType.APPLICATION_JSON)).andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    void getEmployeeById() throws Exception{

        MvcResult result=  mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employee.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.empId").value(employee.getEmpId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empName").value(employee.getEmpName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empMobile").value(employee.getEmpMobile()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empSalary").value(employee.getEmpSalary()))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    void updateEmployee() throws Exception{
        String employeeDtoJson = objectMapper.writeValueAsString(employeeDto1);

       MvcResult result= mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeDtoJson)).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void deleteEmployee() throws  Exception{



        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", employee.getId())
                .contentType(MediaType.APPLICATION_JSON)).
                andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }

}


