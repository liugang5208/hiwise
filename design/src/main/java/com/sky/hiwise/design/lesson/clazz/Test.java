package com.sky.hiwise.design.lesson.clazz;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Child a = new Child();
        Child b = new Child("abc", 123);
//        Child.map.put("b", "test");
//        Child.map.forEach((key, value)-> System.out.println("key:" + key + ",value:" +value));
    }
}

class Parent {

    public String name;

    public int age;

    {
        System.out.println("parent block");
    }

    static {
        System.out.println("parent static block");
    }

    public Parent(){
        System.out.println("parent constructor");
    }

    public Parent(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("parent constructor2");
    }

    public void test() {
        System.out.println("parent test");
    }
}

class Child extends Parent {

    public String name;

    public static final Map<String, String> map;

    {
        System.out.println("child block");
    }

    static {
        System.out.println("child static block");
        map = new HashMap<>();
        map.put("a", "hello");
        map.put("b", "world");
    }

    public Child(){
        System.out.println("child constructor");
    }

    public Child(String name, int age) {
        System.out.println("child constructor2");
    }

    public void test() {
        super.test();
        System.out.println("child test");
    }
}
