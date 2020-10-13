package com.sky.hiwise.netty.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

public class ServerInitialzer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //pipeline.addLast(new ServerHandler());
        //指定对哪种对象进行解码
        pipeline.addLast("decoder", new ProtobufDecoder(Message.GroupMessage.getDefaultInstance()));
        pipeline.addLast(new ProtoBufServerHandler());
    }
}
