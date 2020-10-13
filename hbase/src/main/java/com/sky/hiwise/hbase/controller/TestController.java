package com.sky.hiwise.hbase.controller;

import com.sky.hiwise.hbase.domain.User;
import com.sky.hiwise.hbase.service.HBaseService;
import com.sky.hiwise.hbase.domain.UserRowMapper;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Thread.sleep;

@RestController
@Validated
public class TestController {

    @Autowired
    Connection hbaseConnection;

    @Autowired
    HbaseTemplate hbaseTemplate;

    @Autowired
    HBaseService hBaseService;

//    @GetMapping("/index")
//    public String index() {
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            Result ret = hBaseService.getRow(hbaseConnection, "test", "row2");
//            System.out.println(ret);
//        }
//
//        long end = System.currentTimeMillis();
//        return String.valueOf(end - start);
//    }

//    @GetMapping("/index3")
//    public String index3() throws Exception {
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            User user = hBaseService3.getRow( "test", "row2", new UserRowMapper());
//            System.out.println(user);
//        }
//        long end = System.currentTimeMillis();
//        return String.valueOf(end - start);
//    }

    @GetMapping("/index2")
    public String index2() {
        hbaseTemplate.find("test", "cf", new RowMapper<String>() {
            @Override
            public String mapRow(Result result, int rowNum) throws Exception {
                System.out.println(result + "..." + rowNum);
                return "success";
            }
        });
        return "success";
    }

    @GetMapping("/save")
    public String save() {
        hbaseTemplate.put("test", "row3", "cf", "id", "2".getBytes());
        hbaseTemplate.put("test", "row3", "cf", "name", "test3".getBytes());
        hbaseTemplate.put("test", "row3", "cf", "email", "test3@163.com".getBytes());
        return "success";
    }
//
//    @GetMapping("/getRows")
//    public List<User> getRows() throws Exception {
//        List<String> rows = new ArrayList<>();
//        rows.add("row2");
//        rows.add("row3");
//        List<User> list = hBaseService2.getRows("test", rows, new UserRowMapper());
//        return list;
//    }

    @GetMapping("/put")
    public boolean putRows(){
        String tableName = "test";
        String cf = "cf";
        String row = "rowkey2";
        boolean ret = hBaseService.putRow("test", "rowkey2", "cf", "id", "2");
        boolean ret2 = hBaseService.putRow("test", "rowkey2", "cf", "name", "test2");
        boolean ret3 = hBaseService.putRow("test", "rowkey2", "cf", "email", "test2@163.com");
        System.out.println(ret);
        System.out.println(ret2);
        System.out.println(ret3);
        User user = hBaseService.getRow("test", "rowkey2", new UserRowMapper());
        System.out.println(user);
        return ret;
    }
}
