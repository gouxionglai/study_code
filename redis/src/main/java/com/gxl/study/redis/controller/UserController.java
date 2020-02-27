package com.gxl.study.redis.controller;

import com.gxl.study.redis.model.User;
import com.gxl.study.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gouxi
 * @description
 * @since 2020/2/20
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get/{id}")
    @ResponseBody
    public Object getUser(@PathVariable("id") Integer id){
        return userService.getUser(id);
    }

    @GetMapping("/to_login")
    public String to_login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Object login(User user){
//        Map<String,Object> result = new HashMap<>();
//        result.put("code",1);
//        result.put("msg","success");
//        return result;
        return userService.checkFilter(user.getUname(),user.getUpass());
    }
}
