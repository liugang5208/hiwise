package com.sky.hiwise.lessons.consumer;

import com.sky.hiwise.lessons.common.RedissonManager;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Tasks {

    public static final String TASK_LOCK = "task_lock";

    @Autowired
    private RedissonManager redissonManager;

    //@Scheduled(cron = "*/10 * * * * *")
    public void test() {
        log.info("current time: {}", System.currentTimeMillis());
        RLock lock = redissonManager.getRedisson().getLock(TASK_LOCK);
        boolean getLock = false;
        try {
            if(getLock = lock.tryLock(0,50, TimeUnit.SECONDS)){
                log.info("Redisson获取到分布式锁:{},ThreadName:{}",TASK_LOCK,Thread.currentThread().getName());
            }else{
                log.info("Redisson没有获取到分布式锁:{},ThreadName:{}",TASK_LOCK,Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            log.error("Redisson分布式锁获取异常",e);
        } finally {
            if(!getLock){
                return;
            }
            lock.unlock();
            log.info("Redisson分布式锁释放锁");
        }
    }

}
