package com.sky.hiwise.disruptor.netty;

import com.lmax.disruptor.RingBuffer;
import com.sky.hiwise.disruptor.demo.LongEvent;
import com.sky.hiwise.disruptor.demo.LongEventProducer;
import com.sky.hiwise.disruptor.entity.TranslatorData;
import com.sky.hiwise.disruptor.server.MessageProducer;
import com.sky.hiwise.disruptor.server.RingBufferWorkerPoolFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.util.UUID;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

//         TranslatorData request = (TranslatorData)msg;
//         System.err.println("Sever端: id= " + request.getId()
//         + ", name= " + request.getName()
//         + ", message= " + request.getMessage());
//         //数据库持久化操作 IO读写 ---> 交给一个线程池 去异步的调用执行
//         TranslatorData response = new TranslatorData();
//         response.setId("resp: " + request.getId());
//         response.setName("resp: " + request.getName());
//         response.setMessage("resp: " + request.getMessage());
//         //写出response响应信息:
//         ctx.writeAndFlush(response);


        // Get the ring buffer from the Disruptor to be used for publishing.
//        RingBuffer<LongEvent> ringBuffer = DisruptorServer.getInstance().getDisruptor().getRingBuffer();
//
//        LongEventProducer producer = new LongEventProducer(ringBuffer);
//
//        ByteBuffer bb = ByteBuffer.allocate(8);
//        bb.putInt(0, 12345);
//        producer.onData(bb);


        // 定义发送的数据消息
//        ByteBuf content = Unpooled.copiedBuffer("Hello netty~", CharsetUtil.UTF_8);
//
//        // 构建一个http response
//        FullHttpResponse response =
//                new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
//                        HttpResponseStatus.OK,
//                        content);
//        // 为响应增加数据类型和长度
//        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
//        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
//
//        // 把响应刷到客户端
//        ctx.writeAndFlush(response);


        TranslatorData request = new TranslatorData();
        request.setId(UUID.randomUUID().toString());
        request.setMessage("test message");
        request.setName("test name");
        //自已的应用服务应该有一个ID生成规则
        String producerId = "code:sessionId:001";
        MessageProducer messageProducer = RingBufferWorkerPoolFactory.getInstance().getMessageProducer(producerId);
        messageProducer.onData(request, ctx);

    }

}
