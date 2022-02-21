package com.sky.hiwise.algorithms.leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2022-02-08 15:20
 **/
public class FindMinFibNumSumK1414 {
    /**
     * 1414. 和为 K 的最少斐波那契数字数目
     * 给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
     * 斐波那契数字定义为：
     * F1 = 1
     * F2 = 1
     * Fn = Fn-1 + Fn-2 ， 其中 n > 2 。
     * 数据保证对于给定的 k ，一定能找到可行解。
     * 示例 1：
     * 输入：k = 7
     * 输出：2
     * 解释：斐波那契数字为：1，1，2，3，5，8，13，……
     * 对于 k = 7 ，我们可以得到 2 + 5 = 7 。
     * 示例 2：
     * 输入：k = 10
     * 输出：2
     * 解释：对于 k = 10 ，我们可以得到 2 + 8 = 10 。
     */
    public int findMinFibonacciNumbers(int k) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int a = 1, b = 1;
        while (a + b <= k) {
            int c = a + b;
            list.add(c);
            a = b;
            b = c;
        }
        int ans = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            int num = list.get(i);
            if (k >= num) {
                k -= num;
                ans++;
            }
        }
        return ans;
    }
}
