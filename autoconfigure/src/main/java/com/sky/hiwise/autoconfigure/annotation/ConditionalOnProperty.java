package com.sky.hiwise.autoconfigure.annotation;

import com.sky.hiwise.autoconfigure.condition.OnPropertyCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(OnPropertyCondition.class)
public @interface ConditionalOnProperty {

    String name();
}
