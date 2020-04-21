package com.gxl.study.mq4_springcloud.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author gouxi
 * @description
 * @since 2020/4/17
 */
public interface InputBarista {
    String INPUT_CHANNEL = "input_channel";

    //注意注解中的通道名字必须和配置文件中的一致
    @Input(InputBarista.INPUT_CHANNEL)
    SubscribableChannel loginput();


}
