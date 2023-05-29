package com.sky.hiwise.youli.controller;


import com.sky.hiwise.youli.domain.YouLi;
import com.sky.hiwise.youli.mapper.YouLiMapper;
import com.sky.hiwise.youli.service.YouLiService;
import com.sky.hiwise.youli.task.AtomicId;
import com.sky.hiwise.youli.task.CrawlTask;
import com.sky.hiwise.youli.util.HttpClientResult;
import com.sky.hiwise.youli.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("/you")
@Slf4j
public class YouLiController  {

    @Autowired
    YouLiService youLiService;

    @Autowired
    YouLiMapper youLiMapper;

    @Autowired
    ExecutorService crawlTaskService;

    @GetMapping("/crawl/{id}")
    public String crawlById(@PathVariable("id") Integer projectId) {

        try {
            HttpClientResult result = HttpClientUtils.doPost(AtomicId.URL_PRIFIX + projectId);
            System.out.println(result);
            return "success";
        } catch (Exception e) {
            log.error("{}", projectId, e);
            return "get fail";
        }
    }

    @GetMapping("/crawl")
    public String crawling() throws Exception {
//        for (int i = 0; i < 5; i++) {
//            crawlTaskService.submit(new CrawlTask(youLiService));
//        }
        crawlTaskService.submit(new CrawlTask(youLiService));
        return "success";
    }

//    public static void main(String[] args) throws Exception {
//        //xpath  casper js
//        //chrome headless
//
//        for (int i = 0; i < 300; i++) {
//            Integer id = AtomicId.getId();
//            String url = AtomicId.URL_PRIFIX + id;
//            HttpClientResult result = HttpClientUtils.doGet(url);
//            Document document = Jsoup.parse(result.getContent());
//            Element baseInfo = document.getElementById("base-info");
//
//            System.out.println("deal : " + id);
//            if (baseInfo == null) {
//                System.out.println(result);
//                HtmlUnitDriver driver = new HtmlUnitDriver();
//                //必须设置为true,才能执行js代码
//                driver.setJavascriptEnabled(true);
//                driver.get(url);
//                driver.setJavascriptEnabled(false);
//                driver.get(url);
//                String pageSource = driver.getPageSource();
//                System.out.println(pageSource);
//            }
//
//        }
//
//    }

    public static void main(String[] args) throws Exception  {
        HttpClientResult result = HttpClientUtils.doGet("http://127.0.0.1:8000/tsfresh/index?bikeId=8640506626");
        System.out.println(result);
    }


}
