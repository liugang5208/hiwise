package com.sky.hiwise.netty;

import com.sky.hiwise.netty.tcp.NettySocketServer;
import io.netty.buffer.PooledByteBufAllocator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;

@SpringBootApplication
public class NettyApplication {

    public static void main(String[] args) {

        //PooledByteBufAllocator
                //EPollSelectorProvider
        //SelectorProvider

        SpringApplication.run(NettyApplication.class, args);
    }

}
