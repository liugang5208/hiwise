package com.sky.hiwise.design.lesson.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface Testable {

    String type() default  "type1";

    String source() default "source1";
}



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@interface ClassInfo{
    String author() default "Wang";
    String date();
    String comments();
}
