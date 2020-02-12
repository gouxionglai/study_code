package com.gxl.study.springboot;

import com.gxl.study.springboot.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;


/**
 * @author gouxi
 * @description
 * @since 2020/2/12
 */
@SpringBootTest
public class MailTests {

    @Autowired
    MailService mailService;

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void testSendSimpleMail(){
        String to = "352949107@qq.com";
        String subject = "this is java mail from...";
        String content = "内容很简单..";
        mailService.sendSimpleMail(to,subject,content);

    }


    //异步方法无法调用, 因为测试的主线程执行完后就直接关闭了，没等到子线程去异步执行
//    @Test
//    public void sendTemplateMail() {
//        String id = "006";
//        String to = "352949107@qq.com";
//        try {
//            mailService.sendTemplateMail(id,to);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
