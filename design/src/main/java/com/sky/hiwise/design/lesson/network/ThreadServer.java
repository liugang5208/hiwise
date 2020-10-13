package com.sky.hiwise.design.lesson.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadServer {

    public static void main(String[] args) {

        try {
            //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = null;
            //记录客户端的数量
            int count = 0;
            System.out.println("***服务器即将启动，等待客户端连接***");
            // 循环监听等待客户端的连接
            while (true) {
                //2.调用accep()方法开始监听，等待客户端连接
                socket = serverSocket.accept();
                //创建一个新的线程
                ServerThread serverThread = new ServerThread(socket);
                //启动线程
                serverThread.start();
                count ++;
                System.out.println("客户端的数量：" + count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

/**
 * 服务器线程处理类
 */
class ServerThread extends Thread {

    // 和本线程相关的Socket
    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * 线程执行操作，响应客户端请求
     */
    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            // 获取输入流，并读取客户端信息
            is = socket.getInputStream();
            isr = new InputStreamReader(is);//将字节流转换为字符流
            br = new BufferedReader(isr);//为输入流添加缓冲

            //4.获取输出流，响应客户端的请求
            os = socket.getOutputStream();
            pw = new PrintWriter(os);//包装为打印流

            String info = null;
            while((info = br.readLine()) != null) {//循环读取客户端信息
                System.out.println("我是服务器，客户端说：" + info);
                pw.write("服务器已收到信息：" + info);
                pw.println();
                pw.flush();//调用flush()方法将缓冲输出
            }
            socket.shutdownInput();//关闭输入流

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //5.关闭资源
                pw.close();
                os.close();
                br.close();
                isr.close();
                is.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}