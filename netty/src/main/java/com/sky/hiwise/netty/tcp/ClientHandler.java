package com.sky.hiwise.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message.Customer customer = Message.Customer.newBuilder().setId(1234).setName("hello proto buf").build();
        Message.GroupMessage message = Message.GroupMessage.newBuilder().setDataType(Message.GroupMessage.DataType.CustomerType).setCustomer(customer).build();
        ctx.writeAndFlush(message);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelMgr.getInstance().addChannel("client", ctx.channel());
    }
}
