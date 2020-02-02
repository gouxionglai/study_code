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
@MapperScan("com.gxl.study.springboot.mapper")
class SpringbootApplicationTests {

    //注入person
    @Autowired
    Person person;

    @Autowired
    ApplicationContext context;

    @Autowired
    DepartmentMapper departmentMapper;

    //k v 都是Object
    @Autowired
    RedisTemplate redisTemplate;

    //k v 都是String
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    EmployeeMapper employeeMapper;
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


    /**
     * redis常见五大类型
     * String, List列表, Set集合, Hash散列, Zset有序集合
     */
    //验证redis缓存
    @Test
    public void testRedisConnection(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.append("test","world");
        String test = stringStringValueOperations.get("test");
        System.out.println(test);
    }

    @Test
    public void testRedisEmployee(){
        ValueOperations<String, Employee> valueOperations = redisTemplate.opsForValue();
        Employee employee = employeeMapper.getById(2);
        if(employee !=null){
            System.out.println(employee);
            //需要实现序列化接口才能存储。因为默认使用的jdk序列化规则
            valueOperations.set("emp1",employee);
        }
    }
}
