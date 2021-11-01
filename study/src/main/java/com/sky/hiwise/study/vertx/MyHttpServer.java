package com.sky.hiwise.study.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

public class MyHttpServer extends AbstractVerticle {

    public static void main(String[] args) {
        MyHttpServer verticle = new MyHttpServer();
        Vertx vertx = Vertx.vertx();

        // 部署verticle，这里会调用start方法
        vertx.deployVerticle(verticle);
    }

    @Override
    public void start() {
        // 在这里可以通过this.vertx获取到当前的Vertx
        Vertx vertx = this.vertx;

        // 下面可以实现helloworld中相同的功能

        // 创建一个HttpServer
        HttpServer server = vertx.createHttpServer();

        server.requestHandler(request -> {
            // 获取到response对象
            HttpServerResponse response = request.response();

            // 设置响应头
            response.putHeader("Content-type", "text/html;charset=utf-8");

            // 响应数据
            response.end("Hello World");

        });

        server.listen(8889);
    }

}
