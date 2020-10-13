package com.sky.hiwise.mybatis.config;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BootstrapConfiguration {

    /**
     * Exclude specific auto-configuration classes such that they will never be applied.
     */
    Class<?>[] exclude() default {};

}
