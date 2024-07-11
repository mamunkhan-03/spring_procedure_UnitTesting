package com.erainfotechbd.employee.Dao;

import com.erainfotechbd.employee.dto.EmployeeDto;
import com.erainfotechbd.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class EmployeeDao {
    @Autowired
    private EntityManager entityManager;

    public EmployeeDto employeeInformation (EmployeeDto employeeDto) throws JDBCException {

        System.out.println("Employee dto : "+employeeDto);
        EmployeeDto returnObjects=new EmployeeDto();
        StoredProcedureQuery storedProcedureQuery=entityManager.createStoredProcedureQuery("HR.employeeInformation" );

        storedProcedureQuery.registerStoredProcedureParameter("id",Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("empId", Long.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("empName", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("empMobile", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("empSalary", Long.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("id", employeeDto.getId());
        storedProcedureQuery.setParameter("empMobile", employeeDto.getEmpMobile());


        //it will be comment
//       storedProcedureQuery.setParameter("empId", employeeDto.getEmpId());
//        storedProcedureQuery.setParameter("empName", employeeDto.getEmpName());

//        storedProcedureQuery.setParameter("empSalary", employeeDto.getEmpSalary());



        returnObjects.setEmpId((Long) storedProcedureQuery.getOutputParameterValue("empId"));
        returnObjects.setEmpName((String) storedProcedureQuery.getOutputParameterValue("empName"));
        //returnObjects.setEmpMobile((String) storedProcedureQuery.getOutputParameterValue("empMobile"));
        returnObjects.setEmpSalary((Long) storedProcedureQuery.getOutputParameterValue("empSalary"));

        return returnObjects;
    }

}
