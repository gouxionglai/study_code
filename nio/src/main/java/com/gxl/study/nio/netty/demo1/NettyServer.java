package com.gxl.study.nio.netty.demo1;

import com.gxl.study.nio.netty.demo1.serverHandler.SimpleServerHandler;
import com.gxl.study.nio.netty.demo1.serverHandler.TimeServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author weilai
 * @description
 * @since 2019/12/10
 */
public class NettyServer {
    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8008;
        }
        new NettyServer(port).start();
    }
    private int port;
    public NettyServer(int port){
        this.port=port;
    }

    public void start() throws Exception{
        // 1 创建线两个多线程事件循环器
        // 一个是用于处理服务器端接收客户端连接的
        // 一个是进行网络通信的（网络读写的）
        EventLoopGroup bossGroop = new NioEventLoopGroup();
        EventLoopGroup workerGroop = new NioEventLoopGroup();

        try {
            // 2 创建辅助工具类，用于服务器通道的一系列配置
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 绑定俩个线程组
            bootstrap.group(bossGroop,workerGroop)
                    .channel(NioServerSocketChannel.class) //指定nio模式
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .option(ChannelOption.SO_BACKLOG,1024)  //设置tcp缓冲区
                    .option(ChannelOption.SO_RCVBUF,32*1024)    //设置receiveBuff长度
                    .childOption(ChannelOption.SO_SNDBUF,32*1024)    //设置sendBuff长度
                    .childOption(ChannelOption.SO_KEEPALIVE,true)   //保持连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            //3 在这里配置具体数据接收方法的处理
                            //ChannelPipeline 是一种双向链表  addLast是添加到末尾
                            socketChannel.pipeline().addLast(new TimeServerHandler());
                        }
                    });
            // 4 绑定端口，开始接收进来的连接
            ChannelFuture cf1 = bootstrap.bind(port).sync();
            cf1.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            // 5 等待关闭
            workerGroop.shutdownGracefully();
            bossGroop.shutdownGracefully();
        }
    }
}
