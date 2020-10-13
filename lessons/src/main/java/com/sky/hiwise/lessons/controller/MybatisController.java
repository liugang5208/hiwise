package com.sky.hiwise.lessons.controller;


import com.sky.hiwise.lessons.domain.User;
import com.sky.hiwise.lessons.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/mybatis")
@Slf4j
public class MybatisController {

    @Autowired
    UserMapper userMapper;

    @GetMapping(value = "/")
    public String test() {
        List<User> users = userMapper.findByIds(Arrays.asList(1,2));
        System.out.println(users);
        return "hello";
    }
}
