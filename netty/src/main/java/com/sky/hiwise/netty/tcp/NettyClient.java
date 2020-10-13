package com.sky.hiwise.netty.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClient {

    private static class SingletonNettyClient {
        static final NettyClient instance = new NettyClient();
    }

    public static NettyClient getInstance() {
        return SingletonNettyClient.instance;
    }

    private EventLoopGroup group;
    private Bootstrap bootstrap;
    private ChannelFuture channelFuture;
    private NettyClient() {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new ClientInitializer()); //自定义一个初始化类
    }

    public void connect() {
        try {
            log.info("NettyClient connect ....");
            channelFuture = bootstrap.connect("localhost", 7000).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
