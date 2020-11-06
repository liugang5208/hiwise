package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.*;

public class ReorderRoutes1466 {
    /**
     * 1466. 重新规划路线
     * n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。
     * 去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
     * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
     * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
     * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
     * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
     * 示例 1：
     * 输入：n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
     * 输出：3
     * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
     */
    public int minReorder(int n, int[][] connections) {
        List<Integer>[] sets = new ArrayList[n];
        for (int i = 0; i < connections.length; i++) {
            if (sets[connections[i][0]] == null) {
                sets[connections[i][0]] = new ArrayList<>();
            }
            if (sets[connections[i][1]] == null) {
                sets[connections[i][1]] = new ArrayList<>();
            }
            sets[connections[i][0]].add(i);
            sets[connections[i][1]].add(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visited = new boolean[n];
        int ans = 0;
        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            if (sets[curr] != null) {
                for (int idx : sets[curr]) {
                    if (visited[idx]) {
                        continue;
                    }
                    visited[idx] = true;
                    int a = connections[idx][0];
                    int b = connections[idx][1];
                    ans += a == curr ? 1 : 0;
                    a = (a == curr) ? b : a;
                    queue.add(a);
                }
            }
        }
        return ans;
    }
    /**
     * 思路
     * n 个城市，n-1 条路，路线网形成一颗树
     * 都要去往城市 0
     * 路线不能改，只能改方向
     * 实际上就是以 0 为根节点的树，往下联通时，捋一遍方向
     */

    public static void main(String[] args) {
        int n = 6;
        int[][] connections = new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}};
        System.out.println((new ReorderRoutes1466()).minReorder(n, connections));
    }


}
