package com.sky.hiwise.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@Slf4j
public class HomeController {

    @RequestMapping("/index")
    public String index(Model model) {
        return "home/index";
    }

    @RequestMapping("/console")
    public String console(Model model) {
        return "home/console";
    }

    @RequestMapping("/homepage1")
    public String homepage1(Model model) {
        return "home/homepage1";
    }

    @RequestMapping("/homepage2")
    public String homepage2(Model model) {
        return "home/homepage2";
    }
}
