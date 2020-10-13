package com.sky.hiwise.design.lesson.clazz;//package com.sky.hiwise.design.lesson.clazz;
//
//public class ObjectTest {
//
//    public static void main(String[] args) {
//        new Child();
//        Child c = new Child("abc", 123);
////        c.test();
//        //System.out.println(Child.password);
//    }
//
//    static int allClicks=0;    // 类变量 静态变量
//    String str="hello world";  // 实例变量 成员变量
//    public void method(){
//        int i =0;  // 局部变量
//    }
//
//}
//
//class Parent {
//
//    public String name;
//
//    public int age;
//
//    public static String password;
//
//    {
//        password = "21212";
//        System.out.println("parent block");
//    }
//
//    static {
//        password = "weee";
//        System.out.println("parent static block");
//    }
//
//    public Parent(){
//        System.out.println("parent constructor");
//    }
//
//    public Parent(String name, int age) {
//        this.name = name;
//        this.age = age;
//        System.out.println("parent constructor2");
//    }
//
//    public void test() {
//        System.out.println("parent test");
//    }
//}
//
//class Child extends Parent {
//
//    public String name;
//
//    public static String childName = "hello";
//
//    {
//        System.out.println("child block");
//    }
//
//    static {
//        System.out.println("child static block");
//    }
//
//    public Child(){
//        System.out.println("child constructor");
//    }
//
//    public Child(String name, int age) {
//        System.out.println("child constructor2");
//    }
//
//    public void test() {
//        super.test();
//        System.out.println("child test");
//    }
//}
