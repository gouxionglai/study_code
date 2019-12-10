package com.gxl.study.nio.aio.client;


import java.util.Scanner;

public class AIOClient2 {
    private static String DEFAULT_HOST = "127.0.0.1";
    private static int DEFAULT_PORT = 8008;
    private static AsyncClientHandler clientHandle;

    public static void start() {
        start(DEFAULT_HOST, DEFAULT_PORT);
    }

    public static synchronized void start(String ip, int port) {
        if (clientHandle != null)
            return;
        clientHandle = new AsyncClientHandler(ip, port);
        new Thread(clientHandle, "Client").start();
    }

    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception {
        if (msg.trim().equals("q")) return false;
        clientHandle.sendMsg(msg);
        return true;
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        AIOClient2.start();
        System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);
        while (AIOClient2.sendMsg(scanner.nextLine())) ;
    }
}