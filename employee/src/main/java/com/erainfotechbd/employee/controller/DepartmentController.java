package com.erainfotechbd.employee.controller;

import com.erainfotechbd.employee.dto.DepartmentDto;
import com.erainfotechbd.employee.dto.EmployeeDto;
import com.erainfotechbd.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/procedure")

    public Map<String , Object> departmentProce (@RequestBody DepartmentDto departmentDto){
        System.out.println("department dto : "+departmentDto);
        return departmentService.departmentProcedure(departmentDto);
    }
}
