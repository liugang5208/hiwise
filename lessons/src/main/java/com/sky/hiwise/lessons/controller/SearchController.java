package com.sky.hiwise.lessons.controller;

import com.sky.hiwise.lessons.repository.ESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    ESRepository esRepository;

    @GetMapping(value = "/")
    public boolean test() {
        Map map = esRepository.searchDataByParam("carsource_service", "car_source_service_type", "7232819");
        System.out.println(map);
        return esRepository.isIndexExist("carsource_service");
    }
}
