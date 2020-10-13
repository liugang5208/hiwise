package com.sky.hiwise.design.lesson.network.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ChannelHandler {

    static byte[] readFromChannel(SocketChannel channel, int buffSize) throws IOException {
        // 创建缓存区
        ByteBuffer buffer = ByteBuffer.allocate(buffSize);
        byte[] result = {};
        while ((channel.read(buffer)) != -1) {
            // 切换为写模式，buffer的position置为0
            buffer.flip();
            // 读取buffer有效数据
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes, 0, bytes.length);
            // 数组拼接
            result = CommonUtil.concat(result, bytes);
            // 清空缓存区
            buffer.clear();
        }
        return result;
    }
}
