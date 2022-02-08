package com.sky.hiwise.flink.source;

import org.apache.commons.collections.IteratorUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import scala.Tuple1;

import java.util.List;

public class SourceTest5_Socket {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.setParallelism(1);
        // 从socket读取数据 nc -l 7777
        DataStream<Demo> dataStream = env.socketTextStream("localhost", 7777).map(item -> {
            String[] lines = item.split(",");
            return new Demo(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]), Long.parseLong(lines[2]));
        });

        OutputTag<DemoSum> demoSumOutputTag = new OutputTag<DemoSum>("DemoSum") {};
        SingleOutputStreamOperator<Demo> test = dataStream.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<Demo>(Time.seconds(1)) {
            @Override
            public long extractTimestamp(Demo item) {
                return item.getTimestamp() != null ? item.getTimestamp() : System.currentTimeMillis();
            }
        }).keyBy(Demo::getId)
                .timeWindow(Time.seconds(5)).process(new ProcessWindowFunction<Demo, Demo, Integer, TimeWindow>() {
            @Override
            public void process(Integer integer, Context context, Iterable<Demo> elements, Collector<Demo> out) throws Exception {
                List<Demo> list = (List<Demo>) IteratorUtils.toList(elements.iterator());
                int sum = 0;
                for (Demo item : list) {
                    sum += item.getNum();
                    out.collect(item);
                }
                DemoSum demoSum = new DemoSum(sum, integer);
                demoSum.setWinStart(context.window().getStart());
                demoSum.setWinEnd(context.window().getEnd());
                for (Demo item : list) {
                    item.setSum(sum);
                    out.collect(item);
                }
                context.output(demoSumOutputTag, demoSum);
                //out.collect(new Tuple2<>(demoSum, list));
            }
        });
        test.print("test");
//        DataStream<Demo> test1 = test.flatMap(new FlatMapFunction<Tuple2<DemoSum, List<Demo>>, Demo>() {
//            @Override
//            public void flatMap(Tuple2<DemoSum, List<Demo>> tuple2, Collector<Demo> collector) throws Exception {
//                for(Demo item : tuple2.f1) {
//                    item.setSum(tuple2.f0.getSum());
//                    item.setWinEnd(tuple2.f0.getWinEnd());
//                    item.setWinStart(tuple2.f0.getWinStart());
//                    collector.collect(item);
//                }
//            }
//        });

        // 打印输出
        //test1.print("test1");

        DataStream<Demo> test2 = test.keyBy(Demo::getNum)
                .timeWindow(Time.seconds(5))
                .process(new ProcessWindowFunction<Demo, Demo, Integer, TimeWindow>() {
                    @Override
                    public void process(Integer integer, Context context, Iterable<Demo> elements, Collector<Demo> out) throws Exception {
                        List<Demo> list = (List<Demo>) IteratorUtils.toList(elements.iterator());
                        for(Demo item : list) {
                            item.setWinStart(context.window().getStart());
                            item.setWinEnd(context.window().getEnd());
                            out.collect(item);
                        }
                    }
                });

        // 打印输出
        test2.print("test2");
        //test.getSideOutput(demoSumOutputTag).print("demoSumOutputTag");

        env.execute();
    }

    static class DemoSum {
        private Integer sum;
        private Integer id;
        private Long winStart;
        private Long winEnd;

        public Long getWinStart() {
            return winStart;
        }

        public void setWinStart(Long winStart) {
            this.winStart = winStart;
        }

        public Long getWinEnd() {
            return winEnd;
        }

        public void setWinEnd(Long winEnd) {
            this.winEnd = winEnd;
        }

        public DemoSum(Integer sum, Integer id) {
            this.sum = sum;
            this.id = id;
        }

        public Integer getSum() {
            return sum;
        }

        public void setSum(Integer sum) {
            this.sum = sum;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "DemoSum{" +
                    "sum=" + sum +
                    ", id=" + id +
                    ", winStart=" + winStart +
                    ", winEnd=" + winEnd +
                    '}';
        }
    }

    static class Demo {
        private Integer id;
        private Integer num;
        private Long timestamp;
        private Integer sum;
        private Long winStart;
        private Long winEnd;

        public Long getWinStart() {
            return winStart;
        }

        public void setWinStart(Long winStart) {
            this.winStart = winStart;
        }

        public Long getWinEnd() {
            return winEnd;
        }

        public void setWinEnd(Long winEnd) {
            this.winEnd = winEnd;
        }

        public Demo(Integer id, Integer num, Long timestamp) {
            this.id = id;
            this.num = num;
            this.timestamp = timestamp;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSum() {
            return sum;
        }

        public void setSum(Integer sum) {
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Demo{" +
                    "id=" + id +
                    ", num=" + num +
                    ", timestamp=" + timestamp +
                    ", sum=" + sum +
                    ", winStart=" + winStart +
                    ", winEnd=" + winEnd +
                    '}';
        }
    }
}
