package com.sky.hiwise.study.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@ComponentScan("com.sky.hiwise.study.spring")
@Import({MyImportBeanDefinitionRegistrar.class})
public class TestConfig {
}
