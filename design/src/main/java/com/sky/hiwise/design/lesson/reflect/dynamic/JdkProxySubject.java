package com.sky.hiwise.design.lesson.reflect.dynamic;

import com.sky.hiwise.design.lesson.reflect.pattern.RealSubject;
import com.sky.hiwise.design.lesson.reflect.pattern.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * aspect
 */
public class JdkProxySubject implements InvocationHandler{

    private RealSubject realSubject;

//    public JdkProxySubject(RealSubject realSubject) {
//        this.realSubject = realSubject;
//    }

    public Subject getInstance(RealSubject realSubject){
        this.realSubject = realSubject;
        Class clazz = this.realSubject.getClass();
        // 参数1：被代理类的类加载器 参数2:被代理类的接口 参数3
        return (Subject) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getInterfaces(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = null;
        try{
            result = method.invoke(realSubject, args);
        }catch (Exception e){
            System.out.println("ex:"+e.getMessage());
            throw e;
        }finally {
            System.out.println("after");
        }
        return result;
    }
}
