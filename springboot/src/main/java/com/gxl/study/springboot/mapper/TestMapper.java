package com.gxl.study.springboot.mapper;

import com.gxl.study.springboot.model.Employee;
import org.springframework.stereotype.Component;

/**
 * @author gouxi
 * @description
 * @since 2020/2/1
 */
@Component
public class TestMapper {

    private final Employee employee =new Employee();

    public Employee getValue(Integer id){
        employee.setLastName("hello world insert==================");
        employee.setId(11111);
        employee.setLastName("wei lai");
        employee.setDepartment(1);
        employee.setGender(1);
        employee.setEmail("test@email.com");
        return employee;
    }

    public Employee updateValue(Integer id){
        employee.setLastName("hello world update==================");
        return employee;
    }

    public Integer deleteValue(Integer id) {
        System.out.println("hello world delete===============");
        employee.setId(null);
        employee.setLastName(null);
        employee.setEmail(null);
        employee.setGender(null);
        employee.setDepartment(null);
        employee.setBirth(null);
        return 1;
    }
}
