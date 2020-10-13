package com.sky.hiwise.design.lesson.stream;

import java.io.*;

public class ReaderWriter {

    public static void main(String[] args) {

        ReaderWriter obj = new ReaderWriter();
        obj.testBuffered();
    }

    /**
     * InputStreamReader 是字节流通向字符流的桥梁：它使用指定的 charset 读取字节并将其解码为字符。它使用的字符集可以由名称指定或显式给定，或者可以接受平台默认的字符集。
     每次调用 InputStreamReader 中的一个 read() 方法都会导致从底层输入流读取一个或多个字节。要启用从字节到字符的有效转换，可以提前从底层流读取更多的字节，使其超过满足当前读取操作所需的字节
     */
    public void testInputStreamReader() {

        try {
            FileInputStream in = new FileInputStream(FileTest.PATH + "/FileTest.java");
            InputStreamReader isr = new InputStreamReader(in);  //编码格式默认文件本身的编码，
//            int c;
//            while((c = isr.read()) != -1) {
//                System.out.print((char)c);
//            }

            FileOutputStream os = new FileOutputStream(FileTest.PATH + "/test.txt");
            OutputStreamWriter osw = new OutputStreamWriter(os);

            // 定义字符数组
            char[] buffer = new char[1024];
            int length = 0;// 定义读入字符数组实际长度
            //批量读取，放入buffer这个字符数组，从0个位置开始，最多放buffer.length个，返回的是对到的字符的个数
            while ((length = isr.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, length));
                // 输出文件到控制台时最好不要使用ln换行打印,否则如果数组长度定义不恰当是可能出现问题的。

                osw.write(buffer, 0 ,length);
                osw.flush();
            }

            isr.close();
            osw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * FileReader是用来读取字符文件的便捷类。此类的构造方法假定默认字符编码和默认字节缓冲区大小都是适当的。
     * 要自己指定这些值，可以先在 FileInputStream 上构造一个 InputStreamReader。
     FileReader用于读取字符流。要读取原始字节流，请考虑使用 FileInputStream
     总结：InputStreamReader 是用来读取原始字节流，可指定编码格式，
     而FileReader是读取字符流，使用系统默认的编码格式，当读取中文文件是易出现乱码问题。
     */
    public void testReader() {

        try {
            // 定义文件输入流
            Reader reader = new FileReader(FileTest.PATH + "/FileTest.java");
            // 定义字符数组
            char[] buffer = new char[1024];
            int length = 0;// 定义读入字符数组实际长度
            while ((length = reader.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, length));
                // 输出文件到控制台时最好不要使用ln换行打印,否则如果数组长度定义不恰当是可能出现问题的。
            }

            // 释放资源(标准如下)
            if (reader != null) {
                reader.close();
                reader = null;// 尽早让其进GC垃圾回收
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testWriter() {
        try {
            // 定义字符输出流
            Writer writer = new FileWriter(FileTest.PATH + "/test.txt");// 默认情况下如果系统已经存在定义文件的话,那么就会覆盖原有文件
            // Writer writer=new FileWriter(outFile,boolean append);//是否使用追加模式
            // 备注:使用什么模式要谨慎,操作之前最好要先判断系统是否已经存在该文件。

            writer.write("helloworld,世界你好");
            writer.append("append");
            writer.flush();//及时刷出数据

            //释放流资源
            if (writer != null) {
                writer.close();//关闭流资源
                writer = null;
            }
            //备注只要是资源的使用大体上都是这个模板释放
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testBuffered() {
        try {
            BufferedReader bfReaed = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(FileTest.PATH + "/FileTest.java")
                    )
            );

            BufferedWriter bfWriter = new BufferedWriter(
                    new OutputStreamWriter(
                           new FileOutputStream(FileTest.PATH + "/test.txt")
                    )
            );

            PrintWriter pw = new PrintWriter(FileTest.PATH + "/test.txt");
            //PrintWriter pw1 = new PrintWriter(OutputStream out, boolean autoFlush);

            String line = null;
            while ((line = bfReaed.readLine())!=null) {//每次读取一行
                System.out.println(line); //上面返回的line中的readLine方法中不包含换行符
                bfWriter.write(line);
                bfWriter.newLine(); //单独写换行操作
                bfWriter.flush();

//                pw.println(line);
//                pw.flush();
            }

            bfReaed.close();
            bfWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 前言：在java中为了提高FileWriter的写入效率，出现了BufferedWriter缓存技术
     缓存区的出现是为了提高流的操作效率而出现的，所以在创建缓存区之前，必须要先有流对象
     字符读取流缓存区
     *  该方法提供了一个每次读取一行内容的方法.readLine()，方便对文本数据的获取。
     */
    public void testBufferedReader() {
        try {
            //创建一个读取流对象和文件相关联
            FileReader fReader = new FileReader("bfWriter.txt");
            //为了提高效率，加入缓存技术，将字符流对象传递缓存对象的构造函数
            BufferedReader bfReaed = new BufferedReader(fReader);
            String line = null;
            while ((line = bfReaed.readLine())!=null) {//每次读取一行
                System.out.println(line); //上面返回的line中的readLine方法中不包含换行符
            }
            bfReaed.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 字符写入流缓存区
     *  在java中为了提高FileWriter的写入效率，出现了BufferedWriter缓存技术
     *  缓存区的出现是为了提高流的操作效率而出现的，所以在创建缓存区之前，必须要先有流对象
     *  该缓存区中提供了一个跨平台的换行符就是.newLine()
     */
    public void testBufferedWriter() {
        try {
            //创建一个字符写入流对象
            FileWriter fWriter = new FileWriter("bfWriter.txt");
            //为了提高字符写入流的效率，加入了缓冲技术
            //只要将需要被提高效率的流对象作为参数传递给缓冲区的构造函数即可
            BufferedWriter  bfWriter =new BufferedWriter(fWriter);
            for (int i = 0; i < 5; i++) {
                bfWriter.write("abcdefg");
                bfWriter.newLine();
            }
            //记住，只要用到缓冲区，就要记得刷新。
    //       fWriter.flush();
            //其实关闭缓存区，就是在关闭缓存区中流对象，所有不再需要关闭fWriter对象
            bfWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
