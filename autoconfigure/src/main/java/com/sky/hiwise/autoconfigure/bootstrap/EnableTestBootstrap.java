package com.sky.hiwise.autoconfigure.bootstrap;

import com.sky.hiwise.autoconfigure.annotation.EnableTest;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableTest
public class EnableTestBootstrap {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableTestBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        // test Bean 是否存在
        String test = context.getBean("test", String.class);
        System.out.println("test Bean : " + test);

        // 关闭上下文
        context.close();
    }
}
