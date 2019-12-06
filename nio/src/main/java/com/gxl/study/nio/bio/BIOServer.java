package com.gxl.study.nio.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author weilai
 * @description 模拟服务器端
 * @since 2019/12/6
 */
public class BIOServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8008);
            Socket socket = server.accept();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
