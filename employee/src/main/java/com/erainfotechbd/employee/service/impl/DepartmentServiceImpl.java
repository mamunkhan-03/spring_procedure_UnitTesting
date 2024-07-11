package com.erainfotechbd.employee.service.impl;


import com.erainfotechbd.employee.Dao.DepartmentDao;
import com.erainfotechbd.employee.dto.DepartmentDto;
import com.erainfotechbd.employee.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class DepartmentServiceImpl implements DepartmentService {

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    private DepartmentDao departmentDao;


    @Override
    public Map<String, Object> departmentProcedure(DepartmentDto departmentDto) {

        Map<String, Object>  mapNew=new HashMap<>();
        System.out.println("department dto : "+departmentDto  );

        DepartmentDto departmentResponse= departmentDao.departmentInformation(departmentDto);

        mapNew.put("managerId", departmentResponse.getManagerId());
        mapNew.put("locationId", departmentResponse.getLocationId());
        return mapNew;


    }
}

