package com.gxl.study.springboot;

import com.gxl.study.springboot.mapper.DepartmentMapper;
import com.gxl.study.springboot.mapper.EmployeeMapper;
import com.gxl.study.springboot.model.Department;
import com.gxl.study.springboot.model.Employee;
import com.gxl.study.springboot.model.Person;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;

@SpringBootTest
class SpringbootApplicationTests {

    //注入person
    @Autowired
    Person person;

    @Autowired
    ApplicationContext context;

    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    void contextLoads() {
        System.out.println(person);
    }


    @Test
    void testConfig(){
        Boolean b = context.containsBean("helloService2");
        System.out.println(b);
        Object object = context.getBean("helloService2");
        Object object2 = context.getBean("helloService2");
        System.out.println(object==object2);
    }



    @Test
    void testDataSource(){
        Department department = new Department();
        department.setDepartmentName("部门一");
        Integer result = departmentMapper.insert(department);
        System.out.println(result);
        List<Department> departments = departmentMapper.getAll();
        System.out.println(departments);
    }
}
