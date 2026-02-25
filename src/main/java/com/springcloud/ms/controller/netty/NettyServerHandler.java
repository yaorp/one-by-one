package com.springcloud.ms.controller.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author: yaorp
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("server:" + ctx);
        // 用缓冲区接受数据
        ByteBuf buf = (ByteBuf) msg;
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.readBytes(bytes);
//        System.out.println("server receive:" + new String(bytes));
//        ctx.write(msg);
        System.out.println("server receive:" + buf.toString(CharsetUtil.UTF_8));
    }

    // 数据读取完毕事件，读取完客户端数据后回复客户端
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("宝塔镇河妖", CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf);
//        ctx.flush();
    }
}
