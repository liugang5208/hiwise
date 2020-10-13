package com.sky.hiwise.netty.controller;

import com.sky.hiwise.netty.tcp.ChannelMgr;
import com.sky.hiwise.netty.tcp.Message;
import com.sky.hiwise.netty.tcp.NettyClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
public class TestController {

    @GetMapping("/instance")
    public String instance() {
        NettyClient.getInstance();
        return "success";
    }

    @GetMapping("/client")
    public String client() {
        ByteBuf buffer = Unpooled.copiedBuffer("hello,server ", Charset.forName("utf-8"));
        ChannelMgr.getInstance().getChannelMap().get("client").writeAndFlush(buffer);
        return "success";
    }

    @GetMapping("/server")
    public String server() {
        ByteBuf buffer = Unpooled.copiedBuffer("server response", Charset.forName("utf-8"));
        ChannelMgr.getInstance().getChannelMap().get("server").writeAndFlush(buffer);
        return "success";
    }

    @GetMapping("/client/proto")
    public String protoClient() {
        Message.Customer customer = Message.Customer.newBuilder().setId(1234).setName("hello proto buf").build();
        Message.GroupMessage message = Message.GroupMessage.newBuilder().setDataType(Message.GroupMessage.DataType.CustomerType).setCustomer(customer).build();
        //ByteBuf buffer = Unpooled.copiedBuffer("hello,server ", Charset.forName("utf-8"));
        ChannelMgr.getInstance().getChannelMap().get("client").writeAndFlush(message);
        return "success";
    }


}
