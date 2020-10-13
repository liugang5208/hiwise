package com.sky.hiwise.sharding.controller;

import com.sky.hiwise.sharding.domain.User;
import com.sky.hiwise.sharding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/test")
    public String test1() {
        String aa = "";
        char[] abc = aa.toCharArray();
        return userService.getById(1).toString();
    }

    @GetMapping("/hello")
    public String test2() {

        return userService.test1();
    }

    @GetMapping("/add")
    public String add() {
        User user = new User();
        user.setAge(119);
        user.setName("test add");
        user.setDescription("test des");
        user.setHeadImg("weweccveh");
        user.setSex(1);
        userService.save(user);
        return user.toString();
    }

}
