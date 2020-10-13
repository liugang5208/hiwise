package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCityNum1334 {

    /**
     * 1334. 阈值距离内邻居最少的城市
     * 有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi 两个城市之间的双向加权边，距离阈值是一个整数 distanceThreshold。
     * 返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。如果有多个这样的城市，则返回编号最大的城市。
     * 注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。
     * 示例 1：
     * 输入：n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
     * 输出：3
     * 解释：城市分布图如上。
     * 每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是：
     * 城市 0 -> [城市 1, 城市 2]
     * 城市 1 -> [城市 0, 城市 2, 城市 3]
     * 城市 2 -> [城市 0, 城市 1, 城市 3]
     * 城市 3 -> [城市 1, 城市 2]
     * 城市 0 和 3 在阈值距离 4 以内都有 2 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dis = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        for (int[] edge : edges) {
            dis[edge[0]][edge[1]] = edge[2];
            dis[edge[1]][edge[0]] = edge[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j || dis[i][k] == Integer.MAX_VALUE || dis[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }

        int ans = 0;
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dis[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= minNum) {
                ans = i;
                minNum = cnt;
            }
        }
        return ans;
    }
    /**
     * Floyd算法
     * Floyd算法又称插点法，其中算法的核心思想是动态规划。
     * 算法步骤
     * 通过已知条件初始化距离矩阵D[n][n]，其中D[i][j]表示，顶点i到顶点j的距离。
     * n个顶点依次作为插入点，例如，k为其中一个顶点，D[i][k] + D[k][j] < D[i][j]，那说明顶点i经过顶点k再到达j，比直接到达j要近。
     * 所以更新D[i][j]：D[i][j] = D[i][k] + D[k][j]。
     * 可以归纳得到状态转移方程：D[i][j] = min(D[i,k]+D[k,j],D[i,j]);
     *题目解析
     * 使用Floyd算法求出各个城市到其它城市的距离，保存在矩阵D[n][n]中。
     * 遍历D[n][n]，统计各个城市在距离不超过 distanceThreshold 的情况下，能到达的其它城市的数量。
     * 返回能到达其它城市最少的城市 ret。
     * 作者：huwt
     * 链接：https://leetcode-cn.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/solution/yu-zhi-ju-chi-nei-lin-ju-zui-shao-de-cheng-shi-flo/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
