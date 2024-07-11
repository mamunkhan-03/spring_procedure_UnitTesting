package com.erainfotechbd.employee.Dao;

import com.erainfotechbd.employee.dto.EmployeeDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
@Slf4j
public class EmployeeEmpIdDao {



        @Autowired
        private EntityManager entityManager;

        public  EmployeeDto employeeInformations (EmployeeDto employeeDto) throws JDBCException {

            System.out.println("Employee dto : "+employeeDto);
            EmployeeDto returnObjects=new EmployeeDto();
            StoredProcedureQuery storedProcedureQuery=entityManager.createStoredProcedureQuery("HR.empInfo" );

            storedProcedureQuery.registerStoredProcedureParameter("id",Long.class, ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("empId", Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("empName", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("empMobile", String.class, ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("empSalary", Long.class, ParameterMode.OUT);

            storedProcedureQuery.setParameter("empId", employeeDto.getEmpId());
            storedProcedureQuery.setParameter("empName", employeeDto.getEmpName());


            //it will be comment
//       storedProcedureQuery.setParameter("empId", employeeDto.getEmpId());
//        storedProcedureQuery.setParameter("empName", employeeDto.getEmpName());

//        storedProcedureQuery.setParameter("empSalary", employeeDto.getEmpSalary());



            returnObjects.setId((Long) storedProcedureQuery.getOutputParameterValue("id"));
            returnObjects.setEmpMobile((String) storedProcedureQuery.getOutputParameterValue("empMobile"));
            //returnObjects.setEmpMobile((String) storedProcedureQuery.getOutputParameterValue("empMobile"));
            returnObjects.setEmpSalary((Long) storedProcedureQuery.getOutputParameterValue("empSalary"));

            return returnObjects;
        }

    }


