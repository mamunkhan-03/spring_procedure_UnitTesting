package com.erainfotechbd.employee.Dao;

import com.erainfotechbd.employee.dto.EmployeeDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
@Repository
@Slf4j
public class EmployeeDaoMobile {

    @Autowired
    EntityManager entityManager;

    EmployeeDto returnObjects=new EmployeeDto();

    public EmployeeDto employeeInfoByMobile(EmployeeDto employeeDto){
        StoredProcedureQuery storedProcedureQuery=entityManager.createStoredProcedureQuery("HR.empInfoMobile");
        storedProcedureQuery.registerStoredProcedureParameter("id", Long.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("empId", Long.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("empName", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("empMobile", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("empSalary", Long.class, ParameterMode.OUT);


        storedProcedureQuery.setParameter("empMobile", employeeDto.getEmpMobile());

        returnObjects.setId((Long) storedProcedureQuery.getOutputParameterValue("id"));
        returnObjects.setEmpId((Long) storedProcedureQuery.getOutputParameterValue("empId"));
        returnObjects.setEmpName((String) storedProcedureQuery.getOutputParameterValue("empName"));
        returnObjects.setEmpSalary((Long) storedProcedureQuery.getOutputParameterValue("empSalary"));

        return returnObjects;
    }
}
