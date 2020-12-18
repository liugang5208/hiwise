package com.sky.hiwise.backend.controller;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sky.hiwise.backend.domain.CompareVO;
import com.sky.hiwise.backend.domain.Result;
import com.sky.hiwise.backend.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiCompareController {

    @RequestMapping("/compare")
    public Result compare(@RequestParam(value = "old_api") String oldApi, @RequestParam(value = "new_api") String newApi) throws Exception {
        log.info("old api : {}", oldApi);
        log.info("new api : {}", newApi);
        String oldResult = HttpClientUtils.doGet(oldApi).getContent();
        String newResult = HttpClientUtils.doGet(newApi).getContent();
        log.info("oldResult : {}", oldResult);
        log.info("newResult : {}", newResult);
        JsonParser parser1 = new JsonParser();
        JsonObject obj1 = (JsonObject) parser1.parse(oldResult);
        JsonParser parser2 = new JsonParser();
        JsonObject obj2 = (JsonObject) parser2.parse(newResult);
        CompareVO compareVO = new CompareVO();
        compareVO.setEquals(obj1.equals(obj2));
        compareVO.setNewResult(newResult);
        compareVO.setOldResult(oldResult);
        return Result.success(compareVO);
    }
}
