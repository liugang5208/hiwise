package com.sky.hiwise.design.lesson.annotation;

import java.lang.reflect.Method;
import java.util.Date;

public class Test {

    public static void main(String[] args) {

        //Date
        getAnnotationInfo(MyTest.class);

//        try {
//            process("com.sky.hiwise.design.lesson.annotation.MyTest");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * getAnnotation(Class<T> annotationType)
     如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null。
     Annotation[] getAnnotations()
     返回此元素上存在的所有注释。
     Annotation[] getDeclaredAnnotations()
     返回直接存在于此元素上的所有注释。
     boolean isAnnotationPresent (Class< ? extends Annotation> annotationType)
     如果指定类型的注释存在于此元素上，则返回 true，否则返回 false。
     * @param clz
     */
    public static void getAnnotationInfo(Class<?> clz) {
        // 判断该类是否有ClassInfo注解
        if(clz.isAnnotationPresent(ClassInfo.class)) {
            ClassInfo classInfo = (ClassInfo) clz.getAnnotation(ClassInfo.class);
            System.out.println(classInfo.author() + " " + classInfo.comments() + " " + classInfo.date());
        }

        Method[] methods = clz.getDeclaredMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(Testable.class)) {
                Testable methodInfo = method.getAnnotation(Testable.class);
                System.out.println(methodInfo.type() + " " + methodInfo.source());
            }
        }
    }

    public static void process(String clazz) throws ClassNotFoundException {

        int passed = 0;
        int failed = 0;
        for (Method m : Class.forName(clazz).getMethods()) {
            if (m.isAnnotationPresent(Testable.class)) {
                try {
                    m.invoke(null);
                    passed ++;
                } catch (Exception e) {
                    System.out.println("方法" + m + "运行失败，异常" + e.getCause());
                    failed ++;
                }
            }
        }
        System.out.println("共运行了：" + (passed + failed) + "个方法" );
        System.out.println("成功了：" + passed + "个方法" );
        System.out.println("失败了：" + failed + "个方法" );
    }

}
