package com.sky.hiwise.study.aop.proxy;

public class ProxySubject implements Subject {

    private RealSubject realSubject;

    public ProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }
    /**
     * 在真实对象request()方法之前或之后，可以执行一些额外的操作
     */
    public void request() throws Exception{
        System.out.println("ProxySubject before....");
        try {
            realSubject.request();
        } catch (Exception e) {
            System.out.println("ex:" + e.getMessage());
            throw e;
        } finally {
            System.out.println("ProxySubject after....");
        }
    }
}
