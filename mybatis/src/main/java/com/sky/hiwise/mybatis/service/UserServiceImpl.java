package com.sky.hiwise.mybatis.service;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.hiwise.mybatis.domain.User;
import com.sky.hiwise.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @DS("master")
    @Override
    public String test1() {
        return userMapper.selectById(1).toString();
    }

    @Override
    public void addTest() {
        System.out.println(1111);
        System.out.println("hello world");
    }

    @Transactional
    @Override
    public void hello() {
        List<User> list = userMapper.selectBatchIds(Arrays.asList(1,2,3,4,5));
        userMapper.selectById(1).toString();
        System.out.println("hello");

    }

    @Override
    public void hello1() {
        userMapper.selectById(1).toString();
        System.out.println("hello1");

    }

}
