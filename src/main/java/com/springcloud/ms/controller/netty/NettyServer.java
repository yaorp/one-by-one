package com.springcloud.ms.controller.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: yaorp
 */
public class NettyServer {

    public static void main(String[] args) {
        // 接收客户端连接
        EventLoopGroup bossGrop = new NioEventLoopGroup();
        // 处理网络操作
        EventLoopGroup workerGrop = new NioEventLoopGroup();

        // 创建服务端启动助手来配置参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGrop, workerGrop)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
//                .childHandler((ChannelInitializer) (sc) -> { //9. 往PipeLine链中添加自定义的handLer类
//         sc.pipeline().addLast(new NettyServerHandler());
//                })
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //给pipeline管道设置处理器
                        socketChannel.pipeline().addLast( new NettyServerHandler());
                    }
                })
    ;
        ;

    }
}
