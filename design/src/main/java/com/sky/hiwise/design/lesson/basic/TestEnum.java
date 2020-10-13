package com.sky.hiwise.design.lesson.basic;

public enum  TestEnum {

    WAIT_CALL(1, "待联系"),
    WAIT_SEE(8, "待看车"),
    SUCCESS(2, "带看成功"),
    FAIL(3, "带看失败"),
    WAIT_CALL_FAIL(9, "待联系失败"),
    WAIT_SEE_FAIL(10, "待看车失败"),
    ;

    private Integer code;

    private String msg;

    TestEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}


enum TrafficLamp {
    RED(30) {
        @Override
        public TrafficLamp getNextLamp() {
            return GREEN;
        }
    }, GREEN(45) {
        @Override
        public TrafficLamp getNextLamp() {
            return YELLOW;
        }
    }, YELLOW(5) {
        @Override
        public TrafficLamp getNextLamp() {
            return RED;
        }
    };

    private int time;

    private TrafficLamp(int time) {
        this.time = time;
    }

    //一个抽象方法
    public abstract TrafficLamp getNextLamp();

}

//虽然枚举类不能继承其他类，但是还是可以实现接口的
interface Behaviour {
    void print();

    String getInfo();
}

enum Color implements Behaviour {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 接口方法
    @Override
    public String getInfo() {
        return this.name;
    }

    // 接口方法
    @Override
    public void print() {
        System.out.println(this.index + ":" + this.name);
    }
}


interface Food {

    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
    }

    enum Dessert implements Food {
        FRUIT, CAKE, GELATO
    }
}
