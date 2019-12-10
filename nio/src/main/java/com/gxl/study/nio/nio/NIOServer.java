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
 *  BIO里面分析了有两个阻塞，第一个设置false是解决accpet的阻塞，第二个设置false是解决read的阻塞
 *
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
                    // byteBuffer.clear()不起作用，并不是清空，而是将游标什么的重置，所以是覆盖使用buffer。
                    // 比如上一个helloworld. 本次预期是123,但是读取结果是123loworld.
                    byteBuffer.clear();//读取之前需要清空，不然后面的会继续使用该buffer中的内容
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
//                        String message = new String(bytes, StandardCharsets.UTF_8);
                        String message = new String(byteBuffer.array(), StandardCharsets.UTF_8);
                        System.out.println(message);
                        socketChannel.write(ByteBuffer.wrap("服务器已经收到消息了".getBytes()));
                    }else{
                        //如果断开连接 应该移除
                        if(len ==-1){
                            list.remove(socketChannel);
                        }
                    }
                }
                SocketChannel accept = server.accept();
                //放入list,统一遍历去获取消息
                if(accept!=null){
                    //这里新加入的socket也必须要设置不阻塞，不然会阻塞在read
                    accept.configureBlocking(false);
                    System.out.println("========加入一个连接");
                    list.add(accept);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
