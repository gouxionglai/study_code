package com.gxl.study.nio.netty.demo2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * MessageEncoder.java
 * encode的顺序和decode顺序保持一致
 * @version 1.0
 */

//简单版
    //重点是：ChannelOutboundHandlerAdapter   写适配器
//public class MessageEncoder extends ChannelOutboundHandlerAdapter {
//    @Override
//    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
//        Message m = (Message) msg;
//        ByteBuf encoded = ctx.alloc().buffer(4);
//        encoded.writeBytes(m.getData().getBytes(StandardCharsets.UTF_8));
//        ctx.write(encoded, promise); // (1)
//    }
//}

public class MessageEncoder extends MessageToByteEncoder<Message> {

    //从Message中获取数据，解析成字节后，写入到ByteBuff中
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        Header header = msg.getHeader();
        out.writeByte(MessageDecoder.PACKAGE_TAG);
        out.writeByte(header.getEncode());
        out.writeByte(header.getEncrypt());
        out.writeByte(header.getExtend1());
        out.writeByte(header.getExtend2());
        out.writeBytes(header.getSessionid().getBytes());
        out.writeInt(header.getLength());
        out.writeInt(header.getCammand());
        out.writeBytes(msg.getData().getBytes("UTF-8"));
    }

}
