package com.sky.hiwise.flink.source;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class SourceTest2_File {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        String classPath = Thread.currentThread().getContextClassLoader().getResource("./").getPath();
        // 从文件读取数据
        DataStream<String> dataStream = env.readTextFile(classPath + "sensor.txt");

        // 打印输出
        dataStream.print();

        env.execute();
    }
}
