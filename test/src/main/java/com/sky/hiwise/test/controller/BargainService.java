package com.sky.hiwise.test.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * CREATE TABLE `product` (
 *   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
 *   `product_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '商品名称',
 *   `price` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品价格',
 *   PRIMARY KEY (`id`)
 * );
 *
 *
 * CREATE TABLE `user_bargain` (
 *   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
 *   `product_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '参与活动的商品',
 *   `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '砍价商品发起的用户ID',
 *   `bargain_count` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '被砍价次数',
 *   `deal_money` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最终交易价格',
 *   `create_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '发起时间',
 *   PRIMARY KEY (`id`),
 *   KEY `product_id` (`product_id`),
 *   KEY `user_id` (`user_id`)
 * );
 *
 *
 * CREATE TABLE `bargain_list` (
 *   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
 *   `user_bargain_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'user_bargain表主键id',
 *   `assist_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '帮助者ID',
 *   `create_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '参与时间',
 *   `bargain_money` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '砍掉价格',
 *   PRIMARY KEY (`id`),
 *   KEY `assist_user_id` (`assist_user_id`),
 *   KEY `user_bargain_id` (`user_bargain_id`)
 * );
 */
//public class BargainService {
//
//    private static final Integer TOP = 10;
//
//    public String bargain(Integer bargainId, Integer userId) throws Exception {
//        if (bargainId <= 0 || userId <= 0 ) {
//            throw new Exception("参数错误");
//        }
//
//        //验证砍价信息是否存在，不存在抛出异常
//        getBargainById(bargainId);
//
//        //从bargain_list获取bargainId 对应的所有砍价记录
//        //获取已经砍价的记录可以增加redis缓存，待砍价成功后清除缓存
//        List<BargainDetail> bargainList = getListByBargainId(bargainId);
//        Set<Integer> assists = bargainList.stream().map(BargainDetail::getAssistUserId).collect(Collectors.toSet());
//        if (assists.contains(userId)) {
//            return "您已经参与过砍价";
//        }
//
//        if(bargainList.size() >= 10) {
//            return "感谢您的帮助，您的好友已经获取该商品的低价";
//        }
//
//        //保存砍价结果信息
//        Integer bargainPrice = TOP - bargainList.size();
//        Integer bargainCount = bargainList.size() + 1;
//        Integer dealMoney = dealMoney - bargainPrice;
//        //以下2个操作需要增加事务
//        //所有的价格操作落库都应该以分为单位 伪代码没有做转化
//        saveUserBargain(bargainCount, dealMoney);
//        saveBargainList(bargainId, userId, bargainPrice);
//
//        return "感谢您帮您的好友砍价" + bargainPrice + "元";
//    }
//}
