package com.gxl.study.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weilai
 * @description 同步非阻塞io
 * @since 2019/12/9
 */
public class NIOServer {
    public static void main(String[] args) {
        List<SocketChannel> list = new ArrayList<>();
        //接收消息暂存
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        try {
            int port = 8008;
            //类似于ServerSocket，可以解阻塞
            ServerSocketChannel server = ServerSocketChannel.open();
            //形成端口
            SocketAddress socketAddress = new InetSocketAddress(port);
            //绑定端口
            server.bind(socketAddress);
            //设置不会阻塞
            server.configureBlocking(false);
            while (true){
                //获取消息
                for(SocketChannel socketChannel:list){
                    int len = socketChannel.read(byteBuffer);
                    //有消息
                    if(len>0){
                        System.out.println("=====reading"+ len);
                        //flip的作用是切换读写模式
                        byteBuffer.flip();
                        //注意这里byte的长度不是1024了。而是读取多少，设置多少长度。或者改为一个个字节的读取
                        byte[] bytes = new byte[len];
                        while (byteBuffer.remaining()>0){
                            byteBuffer.get(bytes);
                        }
                        String message = new String(bytes, StandardCharsets.UTF_8);
                        System.out.println(message);
                    }else{
                        //如果断开连接 应该移除
//                        list.remove(socketChannel);
                    }
                }
                SocketChannel accept = server.accept();
                //放入list,统一遍历去获取消息
                if(accept!=null){
                    //
                    System.out.println("========加入一个连接");
                    list.add(accept);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
