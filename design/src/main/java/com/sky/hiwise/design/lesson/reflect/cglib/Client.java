package com.sky.hiwise.design.lesson.reflect.cglib;


import com.sky.hiwise.design.lesson.reflect.pattern.RealSubject;
import com.sky.hiwise.design.lesson.reflect.pattern.Subject;
import net.sf.cglib.proxy.Enhancer;

/**
 */
public class Client {

    public static void main(String[] args){
        Enhancer enhancer = new Enhancer();
        //生成指定类对象的子类，也就是重写业务中的业务函数
        enhancer.setSuperclass(RealSubject.class);
        //设置回调函数，加入intercept
        enhancer.setCallback(new DemoMethodInterceptor());
        //创建子类对象
        Subject subject = (Subject) enhancer.create();
        subject.hello();
    }

    /**
     * JDK动态代理和cglib代理对比
     * JDK动态代理只能针对有接口的类的接口方法进行动态代理
     * cglib是基于继承来实现代理，无法对static final类进行代理
     * cglib是基于继承来实现代理，无法对private static方法进行代理
     *
     */
}
