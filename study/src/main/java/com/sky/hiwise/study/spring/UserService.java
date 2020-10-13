package com.sky.hiwise.study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service("userService")
@CustomerType("user")
public class UserService extends AbstractBaseService {

//    @Autowired
//    CarService carService;
//
//    public UserService() {
//        System.out.println("UserService construct......");
//    }
//
//    public void getCarService() {
//        System.out.println(carService);
//    }

    @Override
    public void init() {
        System.out.println("CarService init ...");
    }

    @PostConstruct
    public void init1() {
        System.out.println("UserService PostConstruct....");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("UserService destroy....");
    }
}
