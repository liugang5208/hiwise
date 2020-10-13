package com.sky.hiwise.lessons.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Before：方法前执行
 * @AfterReturning：运行方法后执行
 * @AfterThrowing：Throw后执行 方法异常时执行
 * @After：无论方法以何种方式结束，都会执行（类似于finally）
 * @Around：环绕执行
 */
@Aspect
@Component
@Slf4j
public class TestAspect {

    @Pointcut("execution(public * com.sky.lesson.service.*.*(..))")
    public void init() {
    }

    @Before(value = "init()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("doBefore");
    }

    @After(value = "init()")
    public void doAfter() {
        //log.info("请求完成，无论方法以何种方式结束，都会执行");
        System.out.println("doAfter");
    }

    @AfterReturning(returning = "object", value = "init()")
    public void afterReturning(Object object) {
        //记录返回的请求信息
        log.info("response={}", object.toString());
    }
}
