package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestFlightsInKStops787 {

    /**
     * 787. K 站中转内最便宜的航班
     * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
     * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
     * 你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。
     * 如果没有这样的路线，则输出 -1。
     * 示例 1：
     * 输入:
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 1
     * 输出: 200
     * 解释:
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }
        Map<String, Integer> best = new HashMap();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> { return a[0] - b[0]; });
        //按照cost费用优先队列排序 k为经过多少站 place为遍历的站点编号  k+","+place：经过k站到place的最小花费
        pq.add(new int[]{0, 0, src});
        while(!pq.isEmpty()) {
            int[] v = pq.poll();
            int cost = v[0], k = v[1], place = v[2];
            if (k > K + 1 || cost > best.getOrDefault(k+","+place, Integer.MAX_VALUE)) {
                continue;
            }
            if (place == dst) {
                return cost;
            }
            for (int i = 0; i < n; i++) {
                if (graph[place][i] > 0) {
                    int newcost = cost + graph[place][i];
                    int next = k+1;
                    if (newcost < best.getOrDefault(next + "," + i, Integer.MAX_VALUE)) {
                        pq.add(new int[]{newcost, next, i});
                        best.put(next + "," + i, newcost);
                    }
                }
            }
        }
        return -1;
    }
}
