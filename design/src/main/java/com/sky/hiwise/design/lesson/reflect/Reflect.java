package com.sky.hiwise.design.lesson.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Reflect {

    /**
     * 为什么需要反射？
     通过反射，我们能够
     在运行时检测对象的类型；
     动态构造某个类的对象；
     检测类的属性和方法；
     任意调用对象的方法；
     修改构造函数、方法、属性的可见性。
     * @param args
     */
    public static void main(String[] args) {

        String s = "hello";
        printClassMessage(s);
        Integer integer = new Integer(1);
        //printFieldMessage(integer);
        //printConMessage(s);
        //testMethodInvoke();
        test();

    }


    public static void printClassMessage(Object object) {
        Class c = object.getClass();
        //获取类名称
        System.out.println("类名称是：" + c.getName());
        /**
         * 一个成员方法就是一个Method对象
         * getMethods 获取所有public的函数，包括父类继承而来的
         * getDeclaredMethods() 获取该类自己申明的方法（即包括public、private和proteced，但是不包括父类的申明）
         * getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段。
         getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
         同样类似的还有getConstructors()和getDeclaredConstructors()、getMethods()和getDeclaredMethods()，这两者分别表示获取某个类的方法、构造函数。
         */
        Method[] ms = c.getDeclaredMethods();
        for (int i = 0; i < ms.length; i++) {
            //得到方法的返回值类型的类类型
            Class returnType = ms[i].getReturnType();
            System.out.print(returnType.getName() + " ");
            System.out.print(ms[i].getName() + "(");
            //获取参数类型 得到的是参数列表的类型的类类型
            Class[] paramTypes = ms[i].getParameterTypes();
            for (Class cls : paramTypes) {
                System.out.print(cls.getName() + ",");
            }
            System.out.println(")");
        }
    }


    public static void printFieldMessage(Object object) {
        Class c = object.getClass();
        //获取类名称
        System.out.println("类名称是：" + c.getName());
        Field[] fs = c.getDeclaredFields();
        for (Field field : fs) {
            //得到成员变量类型的类类型
            Class fieldType = field.getType();
            String typeName = fieldType.getName();
            //成员变量名称
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName);
        }
    }

    public static void printConMessage(Object object) {
        Class c = object.getClass();
        //获取类名称
        System.out.println("类名称是：" + c.getName());
        Constructor[] cs = c.getDeclaredConstructors();
        for (Constructor constructor : cs) {
            System.out.print(constructor.getName() + "(");
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class cls : paramTypes) {
                System.out.print(cls.getName() + ",");
            }
            System.out.println(")");
        }
    }

    /**
     * 方法反射操作
     */
    public static void testMethodInvoke() {
        try {
            String str = new String("test1");
            Method method = str.getClass().getMethod("length");
            System.out.println(method.invoke(str));
            System.out.println(method.invoke(str.length()));
            Method method1 = str.getClass().getMethod("indexOf", String.class);
            System.out.println(method1.invoke(str, "e"));
            System.out.println(str.indexOf("e"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * 反射的操作都是编译之后的操作
     * 编译之后集合的泛型是去泛型化的
     * java中集合的泛型是防止错误输入的，只在编译阶段有效
     * 通过方法的反射操作可绕过编译
     */
    public static void test() {
        ArrayList list = new ArrayList();
        ArrayList<String> list1 = new ArrayList<>();

        list1.add("hello");
        Class c1 = list.getClass();
        Class c2 = list1.getClass();
        System.out.println(c1 == c2);
        //反射的操作都是编译之后的操作

        try {
            Method m = c2.getMethod("add", Object.class);
            m.invoke(list1, 20);
            System.out.println(list1.size());
            System.out.println(list1);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
