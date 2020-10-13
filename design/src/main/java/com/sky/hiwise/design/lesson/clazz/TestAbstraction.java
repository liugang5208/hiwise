package com.sky.hiwise.design.lesson.clazz;

public class TestAbstraction {

    public static void main(String args[]) {
        Bike obj = new Honda();
        obj.run();
        obj.changeGear();
    }
}

/**
 * 抽象类可以有数据成员，抽象方法，方法体，构造函数甚至main()方法
 */
abstract class Bike {

    public int age;

    Bike() {
        System.out.println("bike is created");
    }

    abstract void run();

    void changeGear() {
        System.out.println("gear changed");
    }
}

class Honda extends Bike {
    void run() {
        System.out.println("running safely..");
    }
}
