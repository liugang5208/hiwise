package com.sky.hiwise.sharding.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.hiwise.sharding.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String test1() {
        return userMapper.selectById(1).toString();
    }
}
