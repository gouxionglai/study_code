package com.gxl.study.springboot.controller;

import com.gxl.study.springboot.model.Employee;
import com.gxl.study.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author gouxi
 * @description
 * @since 2020/2/1
 */
@Controller
public class CacheController {

    @Autowired
    private EmployeeService employeeService;

    //测试添加缓存
    @ResponseBody
    @GetMapping("/get/{id}")
    public Object getEmpById(@PathVariable("id") Integer id){
        Employee employee = employeeService.getById(id);
        return employee;
    }

    //测试更新缓存
    @ResponseBody
    @GetMapping("/update/{id}")
    public Object updateEmpById(@PathVariable("id") Integer id){
        Employee employee = employeeService.update(id);

        Map map;
        return employee;
    }

    //测试删除缓存
    @ResponseBody
    @GetMapping("/delete/{id}")
    public Object deleteEmpById(@PathVariable("id") Integer id){
        Integer i= employeeService.delete(id);
        return i;
    }
}
