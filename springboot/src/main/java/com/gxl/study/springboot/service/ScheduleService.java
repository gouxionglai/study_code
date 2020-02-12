package com.gxl.study.springboot.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author gouxi
 * @description
 * @since 2020/2/12
 */
//开启自动任务
@EnableScheduling
@Service
public class ScheduleService {

    private static int i = 0;

    @Scheduled(cron = "0/5 * * * * ?")
    public void schedule1(){
        System.out.println("=====say hello....."+i+"...."+new Date());
        i++;
    }
}
