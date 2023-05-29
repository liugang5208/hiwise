package com.sky.hiwise.algorithms.leetcode.greedy;

import java.util.Arrays;

/**
 * @date: 2023-05-24 19:37
 **/
public class LCP33 {

    /**
     * LCP 33. 蓄水
     * 给定 N 个无限容量且初始均空的水缸，每个水缸配有一个水桶用来打水，第 i 个水缸配备的水桶容量记作 bucket[i]。小扣有以下两种操作：
     * 升级水桶：选择任意一个水桶，使其容量增加为 bucket[i]+1
     * 蓄水：将全部水桶接满水，倒入各自对应的水缸
     * 每个水缸对应最低蓄水量记作 vat[i]，返回小扣至少需要多少次操作可以完成所有水缸蓄水要求。
     * 注意：实际蓄水量 达到或超过 最低蓄水量，即完成蓄水要求。
     * 示例 1：
     * 输入：bucket = [1,3], vat = [6,8]
     * 输出：4
     * 解释：
     * 第 1 次操作升级 bucket[0]；
     * 第 2 ~ 4 次操作均选择蓄水，即可完成蓄水要求。
     * @param bucket
     * @param vat
     * @return
     */
    public int storeWater(int[] bucket, int[] vat) {
        int maxK = Arrays.stream(vat).max().getAsInt();
        if (maxK == 0) {
            return 0;
        }
        int n = bucket.length;
        int res = Integer.MAX_VALUE;
        for (int k = 1; k <= maxK && k < res; k++) {
            int t = 0;
            for (int i = 0; i < n; i++) {
                t += Math.max(0, Math.ceil((double) vat[i] / k) - bucket[i]);
            }
            res = Math.min(res, t + k);
        }

        return res;
    }
}
