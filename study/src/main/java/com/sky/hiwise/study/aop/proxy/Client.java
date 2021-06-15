package com.sky.hiwise.study.aop.proxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) throws Exception{
        //普通静态代理
        Subject subject = new ProxySubject(new RealSubject());
        subject.request();

        //通过调用Proxy.newProxyInstance生成代理对象
        //方法参数为：1）classLoader  2）要代理的接口 3）代理对象的InvocationHandler
        //(通过方法参数也可以看出来，JDK代理只能通过代理接口来来实现动态代理)
        Subject jdkSubject = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(),
                new Class[]{Subject.class}, new JdkProxySubject(new RealSubject()));
        //调用代理对象的request方法。
        //（根据InvocationHandler接口的定义，可以知道实际调用的是JdkProxySubject里的invoke方法）
        jdkSubject.request();


        //创建一个增强器
        Enhancer enhancer = new Enhancer();
        //设置目标类
        enhancer.setSuperclass(RealSubject.class);
        //设置拦截对象
        enhancer.setCallback(new CglibProxySubject());
        //生成代理对象
        Subject cglibSubject = (Subject) enhancer.create();
        //调用代理对象的request方法
        cglibSubject.request();

    }









    // 类加载 双亲委派模型
    //字节码增强技术
    //https://tech.meituan.com/2019/09/05/java-bytecode-enhancement.html
    //ASM(aop cglib)、Javassist(Dubbo)、javaagent (skywalking)、bytebuddy
}
