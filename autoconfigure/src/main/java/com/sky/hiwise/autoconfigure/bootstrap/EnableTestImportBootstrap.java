package com.sky.hiwise.autoconfigure.bootstrap;

import com.sky.hiwise.autoconfigure.annotation.EnableTestImport;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableTestImport
public class EnableTestImportBootstrap {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableTestImportBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        // hello Bean 是否存在
        String hello = context.getBean("hello", String.class);
        System.out.println("hello Bean : " + hello);

        // 关闭上下文
        context.close();
    }
}
