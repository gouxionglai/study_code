package com.gxl.study.nio.netty.demo1.serverHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @author weilai
 * @description
 * @since 2019/12/12
 */
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channel active... ");

//                ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("====server channelRead");
//        ctx.writeAndFlush("===已经连接上服务器");

        ByteBuf byteBuf = (ByteBuf) msg;
//        //读取多少设置多大
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String body = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("Client :" + body );


        // 向客户端发送消息
        String response = "hello client!";
        // 在当前场景下，发送的数据必须转换成ByteBuf数组
        ByteBuf bufResponse = ctx.alloc().buffer(4 * response.length());
        bufResponse.writeBytes(response.getBytes());
        ctx.writeAndFlush(bufResponse);
//        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("====server channelReadComplete");
        ctx.flush();
//        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("====server exceptionCaught");
        cause.printStackTrace();
        ctx.close();
//        super.exceptionCaught(ctx, cause);
    }
}
