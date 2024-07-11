package com.erainfotechbd.employee;
import com.erainfotechbd.employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class EmployeeApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);

    }






}