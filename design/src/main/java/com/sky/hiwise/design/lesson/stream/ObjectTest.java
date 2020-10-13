package com.sky.hiwise.design.lesson.stream;

import java.io.*;

public class ObjectTest {

    public static void main(String[] args) {

        testFoo();
    }

    /**
     *  Java序列化是指把Java对象转换为字节序列的过程；
     *  而Java反序列化是指把字节序列恢复为Java对象的过程
     *  只有实现了Serializable或Externalizable接口的类的对象才能被序列化
     */
    public static void test() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(FileTest.PATH + "/test.txt"));
            Person per = new Person("test", 12);
            oos.writeObject(per);
            oos.flush();
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FileTest.PATH + "/test.txt"));
            Person per1 = (Person)ois.readObject();
            System.out.println(per1);
            ois.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对子类对象进行序列化操作时，如果父类已经实现了序列化接口，那么子类不需要再实现序列化接口
     * 对子类对象进行反序列化操作时，如果父类没有实现序列化接口，那么父类的构造函数会被调用
     */
    public static void testFoo() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new FileOutputStream(FileTest.PATH + "/test.txt"));
            Foo foo = new Foo2();
            oos.writeObject(foo);
            oos.flush();
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FileTest.PATH + "/test.txt"));
            Foo readFoo = (Foo)ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Foo  {
    public Foo() {
        System.out.println("Foo....");
    }
}

class Foo1 extends Foo {
    public Foo1() {
        System.out.println("Foo1....");
    }
}

class Foo2 extends Foo1 implements Serializable{
    public Foo2() {
        System.out.println("Foo2....");
    }
}