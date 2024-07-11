package com.erainfotechbd.employee.Dao;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class EmployeeDaoAll {

    @Autowired
    EntityManager entityManager;


    
}
