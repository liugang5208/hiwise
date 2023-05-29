package com.sky.hiwise.algorithms.leetcode.stack;

import java.util.Stack;

/**
 * @date: 2022-10-24 17:31
 **/
public class OnlineStockSpan901 {

    /**
     * 901. 股票价格跨度
     * 设计一个算法收集某些股票的每日报价，并返回该股票当日价格的 跨度 。
     * 当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
     * 例如，如果未来 7 天股票的价格是 [100,80,60,70,60,75,85]，那么股票跨度将是 [1,1,1,2,1,4,6] 。
     * 实现 StockSpanner 类：
     * StockSpanner() 初始化类对象。
     * int next(int price) 给出今天的股价 price ，返回该股票当日价格的 跨度 。
     * 示例：
     * 输入：
     * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
     * [[], [100], [80], [60], [70], [60], [75], [85]]
     * 输出：
     * [null, 1, 1, 1, 2, 1, 4, 6]
     */

    public static void main(String[] args) {

    }

    class StockSpanner {

        Stack<int[]> stack;
        int idx;
        public StockSpanner() {
            stack = new Stack<>();
            stack.push(new int[]{-1, Integer.MAX_VALUE});
            idx = -1;
        }

        public int next(int price) {
            idx++;
            while (price >= stack.peek()[1]) {
                stack.pop();
            }
            int ret = idx - stack.peek()[0];
            stack.push(new int[]{idx, price});
            return ret;
        }
    }
}
