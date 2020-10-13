package com.sky.hiwise.test.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@EnableAspectJAutoProxy
public class DebugLogAspect {

//    @Pointcut("execution(public * com.sky.hiwise.test.service.*.filter*(..))")
//    public void point() {
//    }
//
//    @After(value = "point()")
//    public void doAfter() {
//        System.out.println("point DebugLog ...");
//    }

    @After(value = "@annotation(com.sky.hiwise.test.annotation.DebugLog)")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("method include DebugLog ...");
    }
}
