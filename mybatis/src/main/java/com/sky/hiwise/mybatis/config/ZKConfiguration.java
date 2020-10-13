package com.sky.hiwise.mybatis.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZKConfiguration {

    @Bean(name = "zkCuratorClient")
    @ConditionalOnMissingBean
    public CuratorFramework creatCuratorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(10000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();
        return client;
    }


    public static final String zkServerPath = "127.0.0.1:2181";
    public static final Integer timeout = 5000;
    private static Stat stat = new Stat();
    public static void main(String[] args) throws Exception {

        test();

        ZooKeeper zooKeeper = new ZooKeeper(zkServerPath, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("watchedEvent:" + watchedEvent);
            }
        });

        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("自定义watcher");
                System.out.println("watchedEvent:" + watchedEvent);
                if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                    try {
                        byte[] resByte = zooKeeper.getData("/test", this, null);
                        String ret = new String(resByte);
                        System.out.println("getData:" + ret);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        byte[] resByte = zooKeeper.getData("/test", watcher, null);
        String ret = new String(resByte);
        System.out.println(ret);
        Thread.sleep(50000);
    }

    public static void test() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(10000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();
        NodeCache nodeCache = new NodeCache(client, "/test");
        nodeCache.start();
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println(nodeCache.getCurrentData().getPath());
                System.out.println(new String(nodeCache.getCurrentData().getData()));
            }
        });

        Thread.sleep(50000);
    }
}
