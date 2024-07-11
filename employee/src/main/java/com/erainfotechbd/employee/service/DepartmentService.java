package com.erainfotechbd.employee.service;



import com.erainfotechbd.employee.dto.DepartmentDto;

import java.util.Map;

public interface DepartmentService {
    public Map<String, Object> departmentProcedure(DepartmentDto departmentDto);
}
