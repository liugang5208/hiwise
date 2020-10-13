package com.sky.hiwise.design.lesson.clazz;

/**
 * 抽象类也可以用于提供接口的一些实现。
 * 在这种情况下，终端用户可能不会被强制覆盖接口的所有方法。
 */
public class TestAbstraction1 {

    public static void main(String args[]) {
        A a = new M();
        a.a();
        a.b();
        a.c();
        a.d();
    }
}

interface A {
    void a();

    void b();

    void c();

    void d();
}

abstract class B implements A {
    public void c() {
        System.out.println("I am C");
    }
}

class M extends B {
    public void a() {
        System.out.println("I am a");
    }

    public void b() {
        System.out.println("I am b");
    }

    public void d() {
        System.out.println("I am d");
    }
}

