package com.gxl.framework.controller;

import com.gxl.framework.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * @author weilai
 * @description
 * @since 2019/12/18
 */
@Controller
@RequestMapping("/demo")
public class DemoController implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("/test")
    @ResponseBody
    public Object test(){
        System.out.println("hello world!!!");
        String message = "hello world!!!嘿嘿嘿";

        HashMap json=new HashMap();
        json.put("msg",message);
        json.put("code",200);
        return json;
    }


    @RequestMapping(value ="/test2",method = {RequestMethod.POST},params = {"name=嘿嘿嘿","age"},headers = {"User-Agent"})
    public Object test2(Model model, Date date,@RequestParam(name = "age") String userAge){
        String message = "hello world222!!!哈哈哈哈";
        System.out.println(message);
        System.out.println(userAge);
        model.addAttribute("message",message);
        System.out.println(date);
        model.addAttribute("current_time",date);
        //跳转到xx/welcome.jsp页面
        return "welcome";
    }

    /**
     * 获取原生API
     * 会自动实例化参数
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/api")
    public Object test2(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //session可以拿对象 比如登陆之后会往session放token，这里可以取
        //User user = (User)session.getAttribute("user");
        //上下文，可以做更多的事
        ServletContext servletContext = session.getServletContext();
        return null;
    }


    @RequestMapping("/testCookie")
    public Object testCookie(@CookieValue(value = "JSESSIONID")String jValue){

        System.out.println(jValue);
        return null;
    }


    @RequestMapping("/testModelAttribute")
    public Object testModelAttribute(Model model){
        System.out.println("testModelAttribute  执行了...");
//        model.addAttribute("user","这个是user");
        User user = new User();
        user.setId(1L);
        user.setName("嘿嘿嘿");
        user.setSex((byte) 1);
        user.setAge(21);
        model.addAttribute("user",user);
        return "test/target1";
    }


    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("testVoid  执行了...");
        //情况1 转发需要写完整的路径
        request.getRequestDispatcher("/WEB-INF/demo/target1.jsp").forward(request,response);

        //情况2 重定向: 没有WEB-INF了
        response.sendRedirect(request.getContextPath()+"/demo/target1.jsp" );

        //情况3 自己写响应
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("嘿嘿嘿");
    }



    @ResponseBody
    @RequestMapping(value = "/testAjax1")
    public Object testAjax1(User user){

        user.setId(2L);
        return user;
    }
}
