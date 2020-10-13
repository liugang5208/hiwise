package com.sky.hiwise.loadconfigure;

import com.coreos.jetcd.watch.WatchEvent;

/**
 * @author caoxiangmao
 */
public interface EventListener {

    /**
     * 处理收到的事件
     * @param event
     */
    void handle(WatchEvent event);
}
