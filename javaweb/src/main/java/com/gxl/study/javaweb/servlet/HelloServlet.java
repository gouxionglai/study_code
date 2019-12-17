package com.gxl.study.javaweb.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author weilai
 * @description
 * @since 2019/12/17
 */
public class HelloServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("======do post ");
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("logger .... do get ");
        System.out.println("======do get ");
        super.doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("======servlet init====");
        super.init();
    }

    @Override
    public void destroy() {
        System.out.println("======servlet destory====");
        super.destroy();
    }
}
