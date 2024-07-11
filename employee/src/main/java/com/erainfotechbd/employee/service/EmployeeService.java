package com.erainfotechbd.employee.service;


import com.erainfotechbd.employee.dto.EmployeeDto;
import com.erainfotechbd.employee.dto.ResponseEmpDto;
import com.erainfotechbd.employee.entity.Employee;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface EmployeeService {

    ResponseEmpDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto getEmployeeById(Long id);

    ResponseEmpDto updateEmployee(EmployeeDto employeeDto, Long id);

    ResponseEmpDto deleteEmployeeById(Long id);

    EmployeeDto getByEmpId(Long empId);

    EmployeeDto getByEmpMobile(String empMobile);

    EmployeeDto getByEmpName(String empName);

    ResponseEmpDto updateEmpByPatch(EmployeeDto employeeDto, Long id);

//    void deleteByImpId (Long empId );

    public List<Employee> fetchAllEmp();


    public Employee fetchAllEmpByName();

    public Employee fetchAllEmpById();

    public Employee fetchAllEmpByPId(Long id);


    public List<Object[]> fetchEmpProperties(Long id);

    public Employee fetchEmpByIdName(Long id, String empName);

    public Map<String, Object> employeeProcedure(EmployeeDto employeeDto);

    public Map<String, Object> employeeProcedureByEmpId(EmployeeDto employeeDto);

    public Map<String, Object> empProcedureByMobile (EmployeeDto employeeDto);

}