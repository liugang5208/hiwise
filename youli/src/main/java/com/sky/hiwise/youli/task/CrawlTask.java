package com.sky.hiwise.youli.task;

import com.sky.hiwise.youli.service.YouLiService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CrawlTask implements Runnable {

    private YouLiService youLiService;

    public CrawlTask(YouLiService youLiService) {
        this.youLiService = youLiService;
    }

    @Override
    public void run() {
        Integer id = AtomicId.getId();
        while (id > AtomicId.MIN_ID) {
            try {
                youLiService.parseHtml(id);
            } catch (Exception e) {
                log.error("CrawlTask fail, id : {}, ", id, e);
            }
            id = AtomicId.getId();
        }
        log.info("CrawlTask Thread:{} complated", Thread.currentThread().getName());
    }
}
