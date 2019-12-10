package com.gxl.study.nio.bio;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author weilai
 * @description 模拟客户端：原始形式
 * @since 2019/12/6
 */
public class BIOClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8008;
        OutputStream outputStream = null;
        Socket socket = null;
        //建立连接
        try {
            socket = new Socket(host, port);
            outputStream = socket.getOutputStream();
//            System.out.println("start send");
//            String message = "hello client 哈哈哈";
//            outputStream.write(message.getBytes(StandardCharsets.UTF_8));

            //等待控制台输入
            while (true){
                Scanner scanner = new Scanner(System.in);
                outputStream.write(scanner.next().getBytes());
                System.out.println("send successful");
            }

            //接收服务器返回信息
//            InputStream inputStream = socket.getInputStream();
//            byte[] bytes = new byte[1024];
//            int len;
////            while ((len = inputStream.read(bytes))!= -1){
////                System.out.println(new String(bytes,0,len, StandardCharsets.UTF_8));
////            }
////            inputStream.read();
//            inputStream.read(bytes);
//            String message = new java.lang.String(bytes, StandardCharsets.UTF_8);
//            System.out.println(message);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
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
        }
    }
}
