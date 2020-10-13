package com.sky.hiwise.test.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//@Inherited
public @interface DebugLog {

    String value() default "";
}
