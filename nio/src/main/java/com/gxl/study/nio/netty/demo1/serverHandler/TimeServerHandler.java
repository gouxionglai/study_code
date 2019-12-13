package com.gxl.study.nio.netty.demo1.serverHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author weilai
 * @description
 * @since 2019/12/13
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 有连接的时候，立马给客户端返回当前时间
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        //监听：发送完后才关闭连接
        //以为发送是异步的，所以直接写ctx.close()可能还没收到就关闭了
        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener((ChannelFutureListener) future -> {
            assert f == future;
//            ctx.close();
        }); // (4)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
//        super.exceptionCaught(ctx, cause);
    }
}
