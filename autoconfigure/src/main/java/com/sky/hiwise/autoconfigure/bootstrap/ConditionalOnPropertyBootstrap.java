package com.sky.hiwise.autoconfigure.bootstrap;

import com.sky.hiwise.autoconfigure.annotation.ConditionalOnProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

public class ConditionalOnPropertyBootstrap {

    @Bean
    @ConditionalOnProperty(name = "test1")
    public String helloWorld() {
        System.out.println("this println hello world");
        return "Hello,World";
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(ConditionalOnPropertyBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        // helloWorld Bean 是否存在
//        String helloWorld = context.getBean("helloWorld", String.class);
//        System.out.println("helloWorld Bean : " + helloWorld);

        // 关闭上下文
        context.close();
    }
}
