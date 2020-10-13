package com.sky.hiwise.design.lesson.stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class FileTest {

    public static String PATH = "/data/workspace/";

    /**
     * java中File类的常用所有方法及其应用
     创建：
     createNewFile()在指定位置创建一个空文件，成功就返回true，如果已存在就不创建，然后返回false。
     mkdir()  在指定位置创建一个单级文件夹。
     mkdirs()  在指定位置创建一个多级文件夹。
     renameTo(File dest)如果目标文件与源文件是在同一个路径下，那么renameTo的作用是重命名， 如果目标文件与源文件不是在同一个路径下，那么renameTo的作用就是剪切，而且还不能操作文件夹。

     删除：
     delete()  删除文件或者一个空文件夹，不能删除非空文件夹，马上删除文件，返回一个布尔值。
     deleteOnExit()jvm退出时删除文件或者文件夹，用于删除临时文件，无返回值。
     判断：
     exists()  文件或文件夹是否存在。
     isFile()  是否是一个文件，如果不存在，则始终为false。
     isDirectory()  是否是一个目录，如果不存在，则始终为false。
     isHidden()  是否是一个隐藏的文件或是否是隐藏的目录。
     isAbsolute()  测试此抽象路径名是否为绝对路径名。
     获取：
     getName()  获取文件或文件夹的名称，不包含上级路径。
     getAbsolutePath()获取文件的绝对路径，与文件是否存在没关系
     length()  获取文件的大小（字节数），如果文件不存在则返回0L，如果是文件夹也返回0L。
     getParent()  返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回null。
     lastModified()获取最后一次被修改的时间。

     文件夹相关：
     static File[] listRoots()列出所有的根目录（Window中就是所有系统的盘符）
     list()  返回目录下的文件或者目录名，包含隐藏文件。对于文件这样操作会返回null。
     listFiles()  返回目录下的文件或者目录对象（File类实例），包含隐藏文件。对于文件这样操作会返回null。
     list(FilenameFilter filter)返回指定当前目录中符合过滤条件的子文件或子目录。对于文件这样操作会返回null。
     listFiles(FilenameFilter filter)返回指定当前目录中符合过滤条件的子文件或子目录。对于文件这样操作会返回null。
     * @param args
     */
    public static void main(String[] args) {

        FileTest obj = new FileTest();
        //obj.test();
        //obj.testFilter();

obj.raf();
//        File file = new File(PATH);
//        try {
//            listDir(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void test() {
        File file = new File(this.getClass().getResource("").getPath());
        String path = file.getAbsolutePath();
        System.out.println(path);
        System.out.println(file.isDirectory());

        File file1 = new File(PATH + "/test.txt");

        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getParent());
        System.out.println(file1.getAbsoluteFile().getParent());
        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
    }

    public void testFilter() {
        File file = new File(PATH);
        String []nameList = file.list((dir, name)-> name.endsWith(".java") || new File(name).isDirectory());
        for (String name : nameList) {
            System.out.println(name);
        }
    }

    public static void listDir(File dir) throws IOException {
        if (!dir.exists()) {
            throw new IllegalArgumentException("目录：" + dir + "不存在");
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir + "不是目录");
        }

        File []files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listDir(file);
                } else {
                    System.out.println(file);
                }
            }
        }
    }

    /**
     *RandomAccessFile类的主要功能是完成随机读取功能，可以读取指定位置的内容。
     之前的File类只是针对文件本身进行操作的，而如果要想对文件内容进行操作，则可以使用RandomAccessFile类，
     此类属于随机读取类，可以随机读取一个文件中指定位置的数据
     */
    public static void raf() {
        try {
            RandomAccessFile raf = new RandomAccessFile(PATH + "/test.txt", "rw");

            System.out.println(raf.getFilePointer());
            raf.write('A');
            System.out.println(raf.getFilePointer());
            raf.write('B');
            int i = 0x7fffffff;
            raf.writeInt(i);

            String s1 = "中";
            byte[] bytes = s1.getBytes("UTF-8");
            System.out.println(bytes.length);
            raf.write(bytes);
            System.out.println(raf.length());
            raf.seek(0);
            byte[] buf = new byte[(int)raf.length()];

            raf.read(buf);
            System.out.println(Arrays.toString(buf));
            raf.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
