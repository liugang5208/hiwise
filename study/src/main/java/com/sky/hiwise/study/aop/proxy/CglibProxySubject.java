package com.sky.hiwise.study.aop.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

public class CglibProxySubject implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before in cglib....");
        Object result = null;
        try {
            //注意：调用的是proxy.invokeSuper来调用目标类的方法
            result = methodProxy.invokeSuper(o, objects);
        } catch (Exception e) {
            System.out.println("ex:" + e.getMessage());
            throw e;
        } finally {
            System.out.println("after in cglib....");
        }
        return null;
    }
}
