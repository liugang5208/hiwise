package com.sky.hiwise.disruptor.server;

import com.lmax.disruptor.RingBuffer;
import com.sky.hiwise.disruptor.entity.TranslatorData;
import com.sky.hiwise.disruptor.entity.TranslatorDataWapper;
import io.netty.channel.ChannelHandlerContext;

public class MessageProducer {

	private String producerId;
	
	private RingBuffer<TranslatorDataWapper> ringBuffer;
	
	public MessageProducer(String producerId, RingBuffer<TranslatorDataWapper> ringBuffer) {
		this.producerId = producerId;
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(TranslatorData data, ChannelHandlerContext ctx) {
		long sequence = ringBuffer.next();
		try {
			TranslatorDataWapper wapper = ringBuffer.get(sequence);
			wapper.setData(data);
			wapper.setCtx(ctx);
		} finally {
			ringBuffer.publish(sequence);
		}
	}

}
