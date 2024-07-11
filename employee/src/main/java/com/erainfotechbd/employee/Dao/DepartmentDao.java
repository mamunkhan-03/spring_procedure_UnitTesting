package com.erainfotechbd.employee.Dao;
import com.erainfotechbd.employee.dto.DepartmentDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class DepartmentDao {

    @Autowired
    private EntityManager entityManager;

    public DepartmentDto departmentInformation (DepartmentDto departmentDto) throws JDBCException {

        DepartmentDto returnObject=new DepartmentDto();
        StoredProcedureQuery storedProcedureQuery=entityManager.createStoredProcedureQuery("HR.departmentInformation" );

        storedProcedureQuery.registerStoredProcedureParameter("deptId", Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("deptName", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("managerId", Long.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("locationId", Long.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("deptId", departmentDto.getDepartmentId());
        storedProcedureQuery.setParameter("deptName", departmentDto.getDepartmentName());

        returnObject.setManagerId((Long) storedProcedureQuery.getOutputParameterValue("locationId"));
        returnObject.setLocationId((Long) storedProcedureQuery.getOutputParameterValue("managerId"));



        return returnObject;
    }




}
