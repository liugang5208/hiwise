package com.sky.hiwise.design.lesson.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientNIO implements Config, Runnable {

    private String serverIp;

    private int port;

    private String msg;

    private int second;

    private ClientNIO(String serverIp, int port) {
        this.serverIp = serverIp;
        this.port = port;
    }

    private ClientNIO second(int second) {
        this.second = second;
        return this;
    }

    private ClientNIO msg(String msg) {
        this.msg = msg;
        return this;
    }

    private void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        try (SocketChannel channel = SocketChannel.open()) {
            if (channel.connect(new InetSocketAddress(serverIp, port))) {
                System.out.println("[" + DateUtil.getCurTimeStr() + "]:连接server成功");

                channel.configureBlocking(false);

                byte[] bytes = msg.getBytes(DEFAULT_ENCODE);
                ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
                buffer.put(bytes);
                buffer.flip();

                sleep(second);

                System.out.println("[" + DateUtil.getCurTimeStr() + "]:client写入消息");
                System.out.println(msg);
                channel.write(buffer);

                channel.shutdownOutput();

                byte[] result = ChannelHandler.readFromChannel(channel, 1024 * 1024);

                String resultStr = new String(result, DEFAULT_ENCODE);
                System.out.println("[" + DateUtil.getCurTimeStr() + "]:server传来消息");
                System.out.println(resultStr);
                System.out.println();
            } else {
                System.out.println("[" + DateUtil.getCurTimeStr() + "]:连接server失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientNIO client_1 = new ClientNIO(DEFAULT_ADDR, DEFAULT_PORT).second(3).msg("我是第一个客户端");
        new Thread(client_1).start();
        ClientNIO client_2 = new ClientNIO(DEFAULT_ADDR, DEFAULT_PORT).second(1).msg("我是第二个客户端");
        new Thread(client_2).start();
    }
}
