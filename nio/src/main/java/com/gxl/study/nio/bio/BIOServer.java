package com.gxl.study.nio.bio;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author weilai
 * @description 同步阻塞io
 * @since 2019/12/6
 */
public class BIOServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        InputStream inputStream = null;
        try {
            int port=8008;
            server = new ServerSocket(port);
            System.out.println("wait connection..");
            //一直监听
            while (true){
                //阻塞一：等待连接
                socket = server.accept();
                System.out.println("connected..");
                // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];
                int len;
                //多线程不安全
//                StringBuilder sb = new StringBuilder();

                //阻塞二：等待发送内容
                System.out.println("wait data..");
//                while ((len = inputStream.read(bytes)) != -1) {
//                    //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
////                    sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
//                    System.out.println(new String(bytes, 0, len, StandardCharsets.UTF_8));
//                }
                len = inputStream.read(bytes);
                System.out.println(new java.lang.String(bytes,0,len, StandardCharsets.UTF_8));
                System.out.println("data accept complete..");
//                System.out.println("====message from client:" + sb.toString());

                //告知客户端消息
                OutputStream outputStream = socket.getOutputStream();
                String returnMessage = "====message from server: the message has been received";
                outputStream.write(returnMessage.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(server != null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        }
    }
}
