package com.sky.hiwise.loadconfigure;

import com.coreos.jetcd.Watch;
import com.coreos.jetcd.watch.WatchEvent;
import com.coreos.jetcd.watch.WatchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class WatcherHelper {
    private Logger logger = LoggerFactory.getLogger(WatcherHelper.class);
    private Watch.Watcher watcher;

    private boolean active = true;
    /**
     * 监听列表
     */
    private List<EventListener> listeners = new ArrayList<>();


    public WatcherHelper(Watch.Watcher watcher) {
        this.watcher = watcher;
        listen();
    }

    private void listen() {
        Thread thread = new Thread(() -> {
            while (active) {
                WatchResponse response = null;
                try {
                    response = watcher.listen();
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }

                if (response == null) {
                    continue;
                }

                List<WatchEvent> events = response.getEvents();
                for (WatchEvent event : events) {
                    for (EventListener listener : listeners) {
                        try {
                            listener.handle(event);
                        } catch (Throwable t) {
                            logger.error(t.getMessage(), t);

                        }
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


    public void addListener(EventListener listener) {
        listeners.add(listener);
    }


    public void close() {
        this.active = false;
    }
}
