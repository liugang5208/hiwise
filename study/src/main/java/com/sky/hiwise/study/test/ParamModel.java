package com.sky.hiwise.study.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体映射注解
 * 配置该注解的参数会使用UnderlineToCamelArgumentResolver类完成装载
 */
@Target(value = ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamModel {

}
