package com.gxl.study.mq4_springcloud.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author gouxi
 * @description
 * @since 2020/4/17
 */
public interface OutputBarista {

    String OUTPUT_CHANNEL = "output_channel";

    //将通道名字赋予给通道
    @Output(OutputBarista.OUTPUT_CHANNEL)
    MessageChannel logoutput();
}
