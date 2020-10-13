package com.sky.hiwise.study.spring;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        int[] ans = new int[set.size()];
        int i = 0;
        for(Integer v : set) {
            ans[i++] = v;
        }

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        ac.getEnvironment().setActiveProfiles();
        //ac.getBean(UserService.class).getCarService();
        ac.getBean(ServiceHandler.class).getServiceFromHolder("userService").init();
        //ServiceHandler.getServiceFromHolder("userService").init();
    }
}
