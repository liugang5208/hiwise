package com.sky.hiwise.design.lesson.clazz;


interface Printable {
    void print();
    default void msg() {
        System.out.println("default method");
    }
}

interface Showable {
    void show();
    void print();
}

public class TestInterface implements Printable, Showable {

    public void print() {
        System.out.println("Hello");
    }

    public void show() {
        System.out.println("Welcome");
    }

    public static void main(String args[]) {
        TestInterface obj = new TestInterface();
        obj.print();
        obj.show();
        obj.msg();
    }
}

