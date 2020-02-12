package com.gxl.study.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author gouxi
 * @description 发送邮件方法
 * @since 2020/2/12
 */
@Service
public class MailService {
    private Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value("${spring.mail.username}")
    private String MAIL_SENDER;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    //异步发送
    @Async
    public void sendSimpleMail(String subject, String content, String... to){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(MAIL_SENDER); // 发送人的邮箱
        message.setSubject(subject); //标题
        message.setTo(to); //发给谁  对方邮箱
        message.setText(content); //内容
        mailSender.send(message); //发送
    }

    /**
     * 发送邮件
     * @param subject 主题
     * @param content 邮件内容，如果是普通邮件就是简单的字符串；如果是复杂邮件则通过templateEngine.process转换
     * @param attachment 附件
     * @param to  接收人数组
     */
    @Async
    public void sendMimeMail(String subject, String content,File attachment,String... to){

        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            //接收人
            mimeMessageHelper.setTo(to);
            //主题
            mimeMessageHelper.setSubject(subject);
            //内容
            if(content !=null){
                mimeMessageHelper.setText(content, true);
            }
            //附件
            if(attachment !=null){
                mimeMessageHelper.addAttachment(attachment.getName(), attachment);
            }
            mailSender.send(mimeMailMessage);
            System.out.println("==============邮件发送成功");
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

    /**
     * 发送邮件模板
     * @param uid userID
     * @param to 接收人
     */
    @Async
    public void sendTemplateMail(String uid,String... to) throws Exception{
        //创建邮件正文
        Context context = new Context();
        context.setVariable("uid", uid);
        //注意：process第一个参数名称要和templates下的模板名称一致。要不然会报错
        try{
            String emailContent = templateEngine.process("mail/emailTemplate", context);
            this.sendMimeMail("这是账号验证模板", emailContent, null, to);
        }catch(Exception e){
            logger.error("邮件发送失败", e.getMessage());
        }
    }
}
