package com.sky.hiwise.design.lesson.clazz;

public class TestInheritance {
    public static void main(String args[]) {
        Cat c = new Cat();
        c.meow();
        c.eat();

        BabyDog d = new BabyDog();
        d.weep();
        d.bark();
        d.eat();
    }
}

class Animal {
    void eat() {
        System.out.println("eating...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("barking...");
    }
}

class BabyDog extends Dog {
    void weep() {
        System.out.println("weeping...");
    }
}

class Cat extends Animal {
    void meow() {
        System.out.println("meowing...");
    }
}
