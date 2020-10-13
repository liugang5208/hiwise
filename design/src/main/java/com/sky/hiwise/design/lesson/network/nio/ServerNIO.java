package com.sky.hiwise.design.lesson.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class ServerNIO implements Config {
    private Selector selector;

    private ServerNIO(int port) {
        try {
            // 创建选择器
            selector = Selector.open();
            // 打开监听通道
            ServerSocketChannel channel;
            channel = ServerSocketChannel.open();
            // 非阻塞模式
            channel.configureBlocking(false);
            // 绑定端口
            channel.socket().bind(new InetSocketAddress(port));
            // 将channel注册到selector中，并且“兴趣模式”为“accept”
            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("[" + DateUtil.getCurTimeStr() + "]:服务端已经启动");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
        while (true) {
            try {
                // select方法会阻塞当前线程
                System.out.println("[" + DateUtil.getCurTimeStr() + "]:开始轮询获取就绪的channel");
                int readyChannels = selector.select();
                if (readyChannels == 0) continue;
                System.out.println("[" + DateUtil.getCurTimeStr() + "]:有chanel就绪啦");

                Set<SelectionKey> keySet = selector.selectedKeys();
                for (SelectionKey key : keySet) {
                    // 删除正在处理的key
                    keySet.remove(key);
                    if (key.isValid()) {
                        // 可接受的（server注册的key）
                        if (key.isAcceptable()) {
                            // 获取注册的server服务端channel通道
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                            // 获取连接的client客户端channel通道
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            System.out.println("[" + DateUtil.getCurTimeStr() + "]:有新的客户端连接啦");
                            // 设置client的channel为非阻塞
                            socketChannel.configureBlocking(false);
                            // 注册当前clientChannel到selector中，并设置clientChannel为可读
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println();
                        }
                        // 可读的（client注册的key）
                        if (key.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            byte[] result = ChannelHandler.readFromChannel(socketChannel, 1024);

                            String resultStr = new String(result, DEFAULT_ENCODE);

                            System.out.println("[" + DateUtil.getCurTimeStr() + "]:client传来消息");
                            System.out.println(resultStr);
                            System.out.println();

                            socketChannel.shutdownInput();

                            System.out.println("[" + DateUtil.getCurTimeStr() + "]:server写入消息");
                            System.out.println(resultStr);
                            System.out.println();

                            ByteBuffer buffer = ByteBuffer.wrap(result);
                            socketChannel.write(buffer);
                            // 当前channel操作完毕后一定要执行清除操作
                            key.cancel();
                            socketChannel.close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }


    public static void main(String[] args) {
        new ServerNIO(DEFAULT_PORT).start();
    }
}
