package com.gxl.study.springboot.service;

import com.gxl.study.springboot.mapper.EmployeeMapper;
import com.gxl.study.springboot.mapper.TestMapper;
import com.gxl.study.springboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author gouxi
 * @description
 * @since 2020/2/1
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private TestMapper testMapper;

    @Cacheable(cacheNames = {"emp"})
    public Employee getById(Integer id){
        Employee employee = testMapper.getValue(id);
//        Employee employee = employeeMapper.getById(id);
        return employee;
    }

    @CachePut(cacheNames = {"emp"}, key = "#id")
    public Employee update(Integer id){
        System.out.println("更新成功");
        return testMapper.updateValue(id);
    }


    @CacheEvict(cacheNames = {"emp"}, key = "#id")
    public Integer delete(Integer id){
        System.out.println("删除成功");
        return testMapper.deleteValue(id);
    }
}
