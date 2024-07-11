package com.erainfotechbd.employee.repository;

import com.erainfotechbd.employee.dto.EmployeeDto;
import com.erainfotechbd.employee.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmpId(Long empId);

    Employee findByEmpMobile (String empMobile);

    Employee findByEmpName (String empName);

    @Query(value="select e from Employee e")
    public List<Employee> fetchAllEmployee();

    @Query(value="select e from Employee e where e.empName=:name")
    public Employee fetchAllEmployeeByName(@Param("name")String empName);

    @Query(value="select e from Employee e where e.empId=:id")
    public Employee fetchAllEmployeeById(@Param("id") Long empId);

    @Query(value="select e from Employee e where e.id=:pid")
    public Employee fetchEmployeePId(@Param("pid") Long id);

   @Query("select e.id, e.empName from Employee e where e.id = :pid")
    public List<Object[]> fetchEmployeePIdCustom(@Param("pid") Long id);

    @Query (value = "select e from Employee e where e.id=:id and e.empName=:name")
    public Employee fetchEmpByTwoProperties (@Param("id") Long id, @Param("name") String empName);






}






