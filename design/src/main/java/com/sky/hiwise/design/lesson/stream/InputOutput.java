package com.sky.hiwise.design.lesson.stream;

import java.io.*;

public class InputOutput {

    public static void main(String[] args) {

        InputOutput obj = new InputOutput();
        obj.testBufferedOutput();
    }

    public void testInput() {
        try{
            FileInputStream rf = new FileInputStream(FileTest.PATH + "/FileTest.java");
            int n = 512;
            byte buffer[] = new byte[n];
            while((rf.read(buffer,0, n) != -1) && (n > 0)){
                System.out.println(new String(buffer));
            }
            System.out.println();
            rf.close();
        } catch(IOException IOe){
            System.out.println(IOe.toString());
        }
    }

    public void testOutput() {
        try {
            System.out.println("please Input from Keyboard");
            int count, n = 512;
            byte buffer[] = new byte[n];
            count = System.in.read(buffer);
            FileOutputStream wf = new FileOutputStream(FileTest.PATH + "/test.txt");
            wf.write(buffer, 0, count);
            wf.close(); // 当流写操作结束时，调用close方法关闭流。
            System.out.println("Save to the test.txt");
        } catch (IOException IOe) {
            System.out.println("File Write Error!");
        }
    }

    /**
     * BufferedInputStream:缓冲字节输入流，是一个高级流(处理流)，与其他低级流配合使用。
     * //创建一个 BufferedInputStream 并保存其参数，即输入流 in，以便将来使用。创建一个内部缓冲区数组并将其存储在 buf 中,该buf的大小默认为8192。
     public BufferedInputStream(InputStream in);
     //创建具有指定缓冲区大小的 BufferedInputStream 并保存其参数，即输入流 in，以便将来使用。创建一个长度为 size 的内部缓冲区数组并将其存储在 buf 中。
     public BufferedInputStream(InputStream in,int size);
     从构造方法中我们可以知道BufferedInputStream没有无参构造方法,它必须传入一个InputStream(一般是FileInputStream)，来一起使用，以提高读写效率。
     常用的方法
     //从该输入流中读取一个字节
     public int read();
     //从此字节输入流中给定偏移量处开始将各字节读取到指定的 byte 数组中。
     public int read(byte[] b,int off,int len);
     */
    public void testBufferedInput() {
        try {
            FileInputStream fis = new FileInputStream(FileTest.PATH + "/FileTest.java");
            BufferedInputStream bis = new BufferedInputStream(fis);
            String content = null;
            //自己定义一个缓冲区
            byte[] buffer = new byte[4096];
            int flag = 0;
            while((flag = bis.read(buffer)) != -1){
                content += new String(buffer, 0, flag);
            }
            System.out.println(content);
            //关闭的时候只需要关闭最外层的流就行了
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * BufferedOutputStream:缓冲字节输出流是一个高级流(处理流)，与其他低级流配合使用。
     构造方法
     //创建一个新的缓冲输出流，以将数据写入指定的底层输出流。
     public BufferedOutputStream(OutputStream out);
     //创建一个新的缓冲输出流，以将具有指定缓冲区大小的数据写入指定的底层输出流。
     public BufferedOutputStream(OutputStream out,int size);
     常用的方法
     //向输出流中输出一个字节
     public void write(int b);
     //将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此缓冲的输出流。
     public void write(byte[] b,int off,int len);
     //刷新此缓冲的输出流。这迫使所有缓冲的输出字节被写出到底层输出流中。
     public void flush();
     */
    public void testBufferedOutput() {
        try {
            FileOutputStream fos = new FileOutputStream(FileTest.PATH + "/test.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            String content = "我是缓冲输出流测试数据！";
            bos.write(content.getBytes(),0, content.getBytes().length);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testCopy() {
        /**
         * 1.先将文件中的内容读入到缓冲输入流中
         * 2.将输入流中的数据通过缓冲输出流写入到目标文件中
         * 3.关闭输入流和输出流
         */
        try {
            long begin=System.currentTimeMillis();
            FileInputStream fis=new FileInputStream(FileTest.PATH + "/FileTest.java");
            BufferedInputStream bis=new BufferedInputStream(fis);

            FileOutputStream fos=new FileOutputStream(FileTest.PATH + "/test.txt");
            BufferedOutputStream bos=new BufferedOutputStream(fos);

            int size = 0;
            byte[] buffer = new byte[10240];
            while((size = bis.read(buffer)) != -1){
                bos.write(buffer, 0, size);
            }
            //刷新此缓冲的输出流，保证数据全部都能写出
            bos.flush();
            bis.close();
            bos.close();
            long end=System.currentTimeMillis();
            System.out.println("使用缓冲输出流和缓冲输入流实现文件的复制完毕！耗时："+(end-begin)+"毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
