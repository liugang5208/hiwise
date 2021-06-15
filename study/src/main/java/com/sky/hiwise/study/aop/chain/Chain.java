package com.sky.hiwise.study.aop.chain;

import java.util.List;

public class Chain {

    //将各个handler保存在handlers这个list中
    //各个handler之间的执行顺序由chain来维持
    private List<ChainHandler> handlers;
    private int index = 0;

    public Chain(List<ChainHandler> handlers) {
        this.handlers = handlers;
    }

    public void proceed() {
        if (index >= handlers.size()) {
            return;
        }
        handlers.get(index++).execute(this);
    }
}
