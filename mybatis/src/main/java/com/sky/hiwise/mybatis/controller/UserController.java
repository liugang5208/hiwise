package com.sky.hiwise.mybatis.controller;

import com.sky.hiwise.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static java.lang.Thread.sleep;
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/test")
    public String test1() {
        System.out.println("==========" + httpServletRequest.getQueryString());
        System.out.println("==========" + httpServletRequest.getRequestURI());

        System.out.println("==========" + httpServletRequest.getRequestURL());

        return userService.getById(1).toString();
    }

    @GetMapping("/hello")
    public String test2() {

        return userService.test1();
    }

    @GetMapping("/hello3")
    public String test3() {
        userService.hello();
        //userService.addTest();
       // userService.hello1();

        return "success";
    }

}
