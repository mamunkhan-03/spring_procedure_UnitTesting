package com.erainfotechbd.employee.dto;

import lombok.Data;
@Data
public class DepartmentDto {

        private  Long departmentId;
        private String departmentName;
        private Long managerId;
        private Long locationId;
}
