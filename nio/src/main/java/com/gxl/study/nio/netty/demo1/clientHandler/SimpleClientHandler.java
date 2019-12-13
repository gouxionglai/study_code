package com.gxl.study.nio.netty.demo1.clientHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;

public class SimpleClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("====client channelActive");
        String msg = "hello Server!";
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
        encoded.writeBytes(msg.getBytes());
        encoded.writeBytes(msg.getBytes());
        ctx.write(encoded);
        ctx.flush();

//        // 发送消息
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入消息内容：");
//        // 有阻塞
//        String message = sc.nextLine();
//        ctx.writeAndFlush(Unpooled.copiedBuffer(message.getBytes()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("====client channelRead");
        try {
            ByteBuf buf = (ByteBuf) msg;

            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);

            String body = new String(req, StandardCharsets.UTF_8);
            System.out.println("server :" + body );
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("====client channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("====client exceptionCaught");
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }
}
