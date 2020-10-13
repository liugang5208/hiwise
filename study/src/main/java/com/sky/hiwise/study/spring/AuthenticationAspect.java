package com.sky.hiwise.study.spring;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AuthenticationAspect {

    @Pointcut("@within(com.sky.hiwise.study.spring.CustomerType)")
    public void init() {
    }

    @Before(value = "init()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("method include AuthenticationAspect ...");
    }
}
