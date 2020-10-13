package com.sky.hiwise.study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carService")
@CustomerType("car")
public class CarService extends AbstractBaseService {

//    @Autowired
//    UserService userService;
//
//    public CarService() {
//        System.out.println("CarService construct......");
//    }

    @Override
    public void init() {
        System.out.println("CarService init ...");
    }
}
