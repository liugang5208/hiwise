package com.sky.hiwise.lessons.util;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.util.List;

public class ZKNodeOperator implements Watcher {

    private ZooKeeper zookeeper = null;

    public ZKNodeOperator() {
        this.zookeeper = (new ZKUtil()).getClient(ZKUtil.zkServerPath, this);
    }

    /**
     *
     * @Title: ZKOperatorDemo.java
     * @Description: 创建zk节点
     */
    public void createZKNode(String path, byte[] data, List<ACL> acls) {

        String result = "";
        try {
            /**
             * 同步或者异步创建节点，都不支持子节点的递归创建，异步有一个callback函数
             * 参数：
             * path：创建的路径
             * data：存储的数据的byte[]
             * acl：控制权限策略
             * 			Ids.OPEN_ACL_UNSAFE --> world:anyone:cdrwa
             * 			CREATOR_ALL_ACL --> auth:user:password:cdrwa
             * createMode：节点类型, 是一个枚举
             * 			PERSISTENT：持久节点
             * 			PERSISTENT_SEQUENTIAL：持久顺序节点
             * 			EPHEMERAL：临时节点
             * 			EPHEMERAL_SEQUENTIAL：临时顺序节点
             */
            result = zookeeper.create(path, data, acls, CreateMode.PERSISTENT);

//			String ctx = "{'create':'success'}";
//			zookeeper.create(path, data, acls, CreateMode.PERSISTENT, new CreateCallBack(), ctx);

            System.out.println("创建节点：\t" + result + "\t成功...");
            new Thread().sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void process(WatchedEvent watchedEvent) {

        System.out.println("watchedEvent:" + watchedEvent.toString());
    }

    public static void main(String[] args) {
        ZKNodeOperator zkServer = new ZKNodeOperator();

        // 创建zk节点
		zkServer.createZKNode("/testnode", "testnode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);

    }
}
