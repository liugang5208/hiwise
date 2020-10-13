package com.sky.hiwise.autoconfigure.annotation;

import com.sky.hiwise.autoconfigure.configuration.TestConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(TestConfiguration.class)
public @interface EnableTest {
}
