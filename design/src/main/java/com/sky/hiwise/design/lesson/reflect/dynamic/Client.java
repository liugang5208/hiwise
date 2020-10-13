package com.sky.hiwise.design.lesson.reflect.dynamic;

import com.sky.hiwise.design.lesson.reflect.pattern.RealSubject;
import com.sky.hiwise.design.lesson.reflect.pattern.Subject;
/**
 * System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
 */
public class Client {

    public static void main(String[] args){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
       // Subject subject = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(),new Class[]{Subject.class},new JdkProxySubject(new RealSubject()));
        Subject subject = (new JdkProxySubject()).getInstance(new RealSubject());
        subject.hello();
        subject.request();
    }
}
