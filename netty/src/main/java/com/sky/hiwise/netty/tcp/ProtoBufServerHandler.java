package com.sky.hiwise.netty.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProtoBufServerHandler extends SimpleChannelInboundHandler<Message.GroupMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.GroupMessage msg) throws Exception {

        log.info("ProtoBufServerHandler : {}", msg);
    }
}
