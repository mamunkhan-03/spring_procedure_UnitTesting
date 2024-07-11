package com.erainfotechbd.employee.service.impl;

import com.erainfotechbd.employee.Dao.EmployeeDao;
import com.erainfotechbd.employee.Dao.EmployeeDaoMobile;
import com.erainfotechbd.employee.Dao.EmployeeEmpIdDao;
import com.erainfotechbd.employee.dto.EmployeeDto;
import com.erainfotechbd.employee.dto.ResponseEmpDto;
import com.erainfotechbd.employee.entity.Employee;
import com.erainfotechbd.employee.exception.ResourceNotFoundException;
import com.erainfotechbd.employee.repository.EmployeeRepository;
import com.erainfotechbd.employee.service.EmployeeService;
import org.hibernate.JDBCException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper mapper;

    @Autowired
    private EmployeeEmpIdDao employeeEmpIdDao;

    @Autowired
    private EmployeeDaoMobile employeeDaoMobile;

    private EmployeeDao employeeDao;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper, EmployeeDao employeeDao) {
        this.employeeRepository = employeeRepository;
        this.mapper=mapper;
        this.employeeDao=employeeDao;
    }



    @Override
    public ResponseEmpDto createEmployee(EmployeeDto employeeDto) {

        ResponseEmpDto responseEmpDto=new ResponseEmpDto();

        Employee employee=mapToEntity(employeeDto);

        try {
            Employee newEmployee= employeeRepository.save(employee);
            responseEmpDto.setResponseCode(1);
            responseEmpDto.setResponseMessage("User data insert successfully!");
        }

        catch (Exception exce){
            exce.printStackTrace();
        }
        return responseEmpDto;

    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees= employeeRepository.findAll();  //collect all the employees
        return employees.stream().map(employee -> mapToDTO(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee", "id",id));
        return mapToDTO(employee);
    }

    @Override
    public ResponseEmpDto updateEmployee(EmployeeDto employeeDto, Long id) {
        //get post by id from the database
       Employee employee= employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee", "id",id));
       ResponseEmpDto responseEmpDto=new ResponseEmpDto();

        employee.setEmpId(employeeDto.getEmpId());
        employee.setEmpName(employeeDto.getEmpName());
        employee.setEmpMobile(employeeDto.getEmpMobile());
        employee.setEmpSalary(employeeDto.getEmpSalary());

        try {
            Employee updateEmployee = employeeRepository.save(employee);
            responseEmpDto.setResponseCode(2);
            responseEmpDto.setResponseMessage("Updated successfully");
        }

        catch (Exception exce){
            exce.printStackTrace();
        }
        return responseEmpDto;
    }

    @Override
    public ResponseEmpDto deleteEmployeeById(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee", "id",id));

        ResponseEmpDto responseEmpDto=new ResponseEmpDto();
        try {
            employeeRepository.delete(employee);
            responseEmpDto.setResponseCode(3);
            responseEmpDto.setResponseMessage("Deleted successfully");
        }
        catch (Exception exce){
            exce.printStackTrace();

        }
       return responseEmpDto;
    }

    @Override
    public EmployeeDto getByEmpId(Long empId) {

        Employee employee=employeeRepository.findByEmpId(empId);
        EmployeeDto employeeDto=mapToDTO(employee);
        return employeeDto;
    }

    @Override
    public EmployeeDto getByEmpMobile(String empMobile) {

        Employee employee=employeeRepository.findByEmpMobile(empMobile);
        return mapToDTO(employee);
    }

    @Override
    public EmployeeDto getByEmpName(String empName) {
        Employee employee=employeeRepository.findByEmpName(empName);
        return mapToDTO(employee);
    }

    @Override
    public ResponseEmpDto updateEmpByPatch(EmployeeDto employeeDto, Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee", "id",id));

        ResponseEmpDto responseEmpDto=new ResponseEmpDto();
        employee.setEmpId(employeeDto.getEmpId());
        employee.setEmpName(employeeDto.getEmpName());
        employee.setEmpMobile(employeeDto.getEmpMobile());
        employee.setEmpSalary(employeeDto.getEmpSalary());

        try {
            Employee updateEmployee = employeeRepository.save(employee);
            responseEmpDto.setResponseCode(4);
            responseEmpDto.setResponseMessage("Updated successfully with patch");
        }
        catch (Exception exce){
            exce.printStackTrace();

        }
        return responseEmpDto;
    }

    @Override
    public List<Employee> fetchAllEmp() {
        return employeeRepository.fetchAllEmployee();
    }

    @Override
    public Employee fetchAllEmpByName() {
        return employeeRepository.fetchAllEmployeeByName("Md Mamun");
    }

    @Override
    public Employee fetchAllEmpById() {
        return employeeRepository.fetchAllEmployeeById(2L);
    }

    @Override
    public Employee fetchAllEmpByPId(Long id) {
        return employeeRepository.fetchEmployeePId(id);
    }

    @Override
    public Employee fetchEmpByIdName(Long id, String empName) {
        return employeeRepository.fetchEmpByTwoProperties(id, empName);
    }




    @Override
    public List<Object[]> fetchEmpProperties(Long id) {

        return employeeRepository.fetchEmployeePIdCustom(id);
    }



    @Override
    public Map<String, Object> employeeProcedure(EmployeeDto employeeDto)  {

        Map<String, Object>  map=new HashMap<>();


            System.out.println("employee dto : "+employeeDto);
            EmployeeDto employeeDtoResponse= employeeDao.employeeInformation(employeeDto);

            map.put("empId", employeeDtoResponse.getEmpId());
            map.put("empName", employeeDtoResponse.getEmpName());
           // map.put("empMobile", employeeDtoResponse.getEmpMobile());
            map.put("empSalary", employeeDtoResponse.getEmpSalary());


//        catch (JDBCException jdbcException){
//            map.put("responseCode : ", 0);
//            map.put("Name : "," Mamun khan");
//            map.put("External message : ", "Jdbc exception happen ");
//            map.put("Internal message : ", jdbcException.getSQLException().getErrorCode());
//
//
//            SQLException cause = (SQLException)  jdbcException.getCause();
//
//            throw new DbException(jdbcException.getSQLException().getMessage().jdbcException);
//
//        }
//
//        catch (Exception e){
//            throw new AppExcepion (e.getMessage(), e);
//        }

        return map;
    }


    public Map<String, Object> employeeProcedureByEmpId(EmployeeDto employeeDto)  {


        Map<String, Object>  map=new HashMap<>();


        System.out.println("employee dto : "+employeeDto);
        EmployeeDto employeeDtoResponse=employeeEmpIdDao.employeeInformations(employeeDto);

        map.put("id", employeeDtoResponse.getId());
        map.put("empMobile", employeeDtoResponse.getEmpMobile());
        // map.put("empMobile", employeeDtoResponse.getEmpMobile());
        map.put("empSalary", employeeDtoResponse.getEmpSalary());

        return map;
    }

    @Override
    public Map<String, Object> empProcedureByMobile(EmployeeDto employeeDto) {

        Map<String , Object> map= new HashMap<>();
        EmployeeDto response=employeeDaoMobile.employeeInfoByMobile(employeeDto);

        map.put("Id" , response.getId());
        map.put("empId" , response.getEmpId());
        map.put("empName" , response.getEmpName());
        map.put("empSalary" , response.getEmpSalary());

        return map;

    }


    // entity into dto
    private EmployeeDto mapToDTO(Employee employee){
        EmployeeDto employeeDto=mapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    //convert dto into entity
    private Employee mapToEntity(EmployeeDto employeeDto){
        Employee employee=mapper.map(employeeDto, Employee.class);
        return employee;

    }
}
