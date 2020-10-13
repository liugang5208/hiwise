package com.sky.hiwise.netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettySocketServer {

    private static class SingletonNettyServer {
        static final NettySocketServer instance = new NettySocketServer();
    }

    public static NettySocketServer getInstance() {
        return SingletonNettyServer.instance;
    }

    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;

    private NettySocketServer() {
        bossGroup = new NioEventLoopGroup(1);
        workGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerInitialzer());

    }

    public void start() {
        try {
            log.info("NettySocketServer start .... ");
            channelFuture = serverBootstrap.bind(7000).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
