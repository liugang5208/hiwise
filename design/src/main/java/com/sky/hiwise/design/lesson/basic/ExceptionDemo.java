package com.sky.hiwise.design.lesson.basic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionDemo {

    public static void main(String[] args) {

        try {
            test6();
        } catch (MyException e) {
            System.out.println(e.getCode());
        }

        //System.out.println(test5());
    }

    /**
     * 检查性异常：最具代表的检查性异常是用户错误或问题引起的异常，这是程序员无法预见的。例如要打开一个不存在文件时，一个异常就发生了，这些异常在编译时不能被简单地忽略。
     * IOException SQLException
     * 运行时异常： 运行时异常是可能被程序员避免的异常。与检查性异常相反，运行时异常可以在编译时被忽略。
     * NullPointerException ArrayIndexOutOfBoundsException ArithmeticException ClassNotFoundException
     * 错误： 错误不是异常，而是脱离程序员控制的问题。错误在代码中通常被忽略。例如，当栈溢出时，一个错误就发生了，它们在编译也检查不到的。
     */
    public static void test1() {
        try{
            int a[] = new int[2];
            System.out.println("Access element three :" + a[3]);
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println("ExceptionDemo thrown  :" + e);
        }
    }

    /**
     * • try -- 用于监听。将要被监听的代码(可能抛出异常的代码)放在try语句块之内，当try语句块内发生异常时，异常就被抛出。
     * • catch -- 用于捕获异常。catch用来捕获try语句块中发生的异常。
     * • finally -- finally语句块总是会被执行。它主要用于回收在try块里打开的物力资源(如数据库连接、网络连接和磁盘文件)。
     * 只有finally块，执行完成之后，才会回来执行try或者catch块中的return或者throw语句，
     * 如果finally中使用了return或者throw等终止方法的语句，则就不会跳回执行，直接停止。
     * • throw -- 用于抛出异常。
     * • throws -- 用在方法签名中，用于声明该方法可能抛出的异常。
     *
     * 使用多重的catch语句 顺序问题：先小后大，即先子类后父类
     */
    public static void test2() {
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("请输入第一个参数：");
            int one = input.nextInt();
            System.out.println("请输入第二个参数：");
            int two = input.nextInt();
            System.out.println("两数相除的结果为：" + one / two);

        } catch(InputMismatchException e){
            e.printStackTrace();
        } catch (ArithmeticException e) {
            System.out.println("error message  :" + e.getMessage());
        } catch (Exception e) {
            System.out.println("未知错误");
            e.printStackTrace();
        }
    }

    /**
     * throw Exception
     */
    public static void test3() {
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("请输入第一个参数：");
            int one = input.nextInt();
            System.out.println(one);
        } catch(InputMismatchException e){
            throw new NullPointerException("输入参数有误");
        } catch (ArithmeticException e) {
            throw e;
        } catch (Exception e) {
            System.out.println("未知错误");
            e.printStackTrace();
        }
    }

    /**
     * throws Exception
     * @throws InterruptedException
     */
    public static void test4() throws NullPointerException, ArithmeticException {
        //Thread.sleep(1000);
        test3();
//        try {
//            test3();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Thread.sleep(1000);
//            System.out.println(e.getMessage());
//            System.out.println(e.getCause());
//            System.out.println(e.toString());
//        }
    }


    /**
     * try{} 里有一个 return 语句，那么紧跟在这个 try 后的 finally{} 里的 code 会不会被执行，什么时候被执行，在 return 前还是后?
     * 答案：会执行，在方法返回调用者前执行。
     * @return
     */
    public static int test5() {
        int a = 1;
        try {
            a = 2;
            return a;
        } finally {
            a = 8;
            //System.out.println(123);
        }

//        try {
//            a = 4;
//        } finally {
//            a = 5;
//            System.out.println(123);
//        }
//        return a;

//        try {
//            System.out.println(12);
//            return a;
//        } finally {
//            System.out.println(34);
//            return 3;
//        }

//        try{
//            int test[] = new int[2];
//            System.out.println(test[3]);
//            return a;
//        } catch(ArrayIndexOutOfBoundsException e){
//            a = 6;
//            return a;
//        } finally {
//            System.out.println("test");
//            a = 7;
//        }
    }

    public static void test6() {
        throw new MyException(1, "test");
    }
}

class MyException extends RuntimeException {
    private Integer code;

    private String message;

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
