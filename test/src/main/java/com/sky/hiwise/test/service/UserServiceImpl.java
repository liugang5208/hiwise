package com.sky.hiwise.test.service;

import com.sky.hiwise.test.annotation.DebugLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserService userService;

    @Override
    public void test() {
        System.out.println("=======test=====");
        userService.test1();
    }
}
