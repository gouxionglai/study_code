package com.gxl.framework.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
//        JSONObject object = JSONObject.
//        JSONObject jsonObject = new JSONObject(json);
//        logger.info(jsonObject.toString());
//        return jsonObject.toString();
    }


    @RequestMapping("/test2")
    public Object test2(Model model){
        String message = "hello world222!!!哈哈哈哈";
        System.out.println(message);
        model.addAttribute("message",message);
        model.addAttribute("current_time",new Date());
        //跳转到xx/welcome.jsp页面
        return "welcome";
    }
}
