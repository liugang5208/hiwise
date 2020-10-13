package com.sky.hiwise.design.lesson.network;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {

        try {
            //1.创建客户端Socket，指定服务器的端口
            Socket socket = new Socket("localhost", 8888);

            new Thread(new ClientThread(socket)).start();

            //2.获取输出流，向服务器端发送信息
            PrintStream ps = new PrintStream(socket.getOutputStream());
            String line = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while ((line = br.readLine()) != null) {
                ps.println(line);
                ps.flush();
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class ClientThread implements Runnable {
    private Socket socket;

    BufferedReader br = null;
    public ClientThread(Socket socket) throws IOException {

        this.socket = socket;
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    @Override
    public void run() {

        try {
            String content = null;
            while ((content = br.readLine()) != null) {
                System.out.println("我是客户端，服务器说：" + content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
