package com.sky.hiwise.design.lesson.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

    /**
     * 为什么需要反射？
     通过反射，我们能够
     在运行时检测对象的类型；
     动态构造某个类的对象；
     检测类的属性和方法；
     任意调用对象的方法；
     修改构造函数、方法、属性的可见性。
     */

    /**
     * 在java.lang.reflect包中有三个重要的类：
     Field：描述类的域
     Method：描述类的方法
     Constructor：描述类的构造器

     对于public域（包括超类成员）：
     getFields
     getMethods
     getConstructors

     对于其它域（包括私有和受保护的成员，不包括超类成员）：
     getDeclaredFields
     getDeclaredMethods
     getDeclaredConstructors

     一个成员方法就是一个Method对象
     getMethods 获取所有public的函数，包括父类继承而来的
     getDeclaredMethods() 获取该类自己申明的方法（即包括public、private和proteced，但是不包括父类的申明）
     getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段。
     getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
     同样类似的还有getConstructors()和getDeclaredConstructors()、getMethods()和getDeclaredMethods()，这两者分别表示获取某个类的方法、构造函数。

     大家在使用Class实例化其他类的对象的时候，一定要自己定义无参的构造函数
     所有类的对象其实都是Class的实例。
     */
    public static void main(String[] args) throws Exception {

        refGetClass();

        // 获取并调用无参构造函数
        refGetPublicConstructor();

        // 获取并调用私有的含参构造函数
        refGetPrivateConstructor();

        // 获取并调用无参方法 fun
        refGetMethodWithNoArg();

        // 获取并调用有参数方法 fun
        refGetMethodWithArg();

        // 获取类的字段
        refGetField();
    }

    private static void refGetClass() throws ClassNotFoundException {
        // 加载类的3种方法
        Class clazz = Class.forName("com.sky.hiwise.design.lesson.reflect.Person");
        Class clazz1 = new Person().getClass();
        Class class2 = Person.class;
        System.out.println();
    }

    // 获取并调用无参构造函数
    private static void refGetPublicConstructor() throws Exception {

        Class clazz = Class.forName("com.sky.hiwise.design.lesson.reflect.Person");
        Constructor c = clazz.getConstructor(null);

        Person p = (Person) c.newInstance(null);
        System.out.println();
    }

    // 获取并调用私有的含参构造函数
    private static void refGetPrivateConstructor() throws Exception {

        Class clazz = Class.forName("com.sky.hiwise.design.lesson.reflect.Person");
        Constructor c = clazz
                .getDeclaredConstructor(new Class[] { String.class });

        // 由于构造函数是 private 的，所以需要屏蔽Java语言的访问检查
        c.setAccessible(true);

        Person p = (Person) c
                .newInstance(new Object[] { "I'm a reflect name!" });
        System.out.println();
    }

    // 获取并调用无参方法 fun
    private static void refGetMethodWithNoArg() throws Exception {

        Class clazz = Class.forName("com.sky.hiwise.design.lesson.reflect.Person");
        Constructor c = clazz.getConstructor(null);
        Person p = (Person) c.newInstance(null);

        Method method = clazz.getMethod("fun", null);
        method.invoke(p, null);
        System.out.println();
    }

    // 获取并调用有参数方法 fun
    private static void refGetMethodWithArg() throws Exception {

        Class clazz = Class.forName("com.sky.hiwise.design.lesson.reflect.Person");
        Constructor c = clazz.getConstructor(null);
        Person p = (Person) c.newInstance(null);

        Method method = clazz.getMethod("fun", new Class[] { String.class });
        method.invoke(p,  "I'm a reflect method!");
        System.out.println();
    }

    // 获取类的字段
    private static void refGetField() throws Exception {

        Class clazz = Class.forName("com.sky.hiwise.design.lesson.reflect.Person");
        Constructor c = clazz
                .getDeclaredConstructor(new Class[] { String.class });
        // 由于构造函数是 private 的，所以需要获取控制权限
        c.setAccessible(true);
        Person p = (Person) c
                .newInstance(new Object[] { "I'm a reflect name!" });

        Field f = clazz.getField("name");
        Object value = f.get(p);
        Class type = f.getType();
        System.out.println(type);

        if (type.equals(String.class)) {
            System.out.println((String) value);
        }
        System.out.println();
    }








}
