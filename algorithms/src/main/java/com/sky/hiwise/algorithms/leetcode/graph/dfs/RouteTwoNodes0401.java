package com.sky.hiwise.algorithms.leetcode.graph.dfs;

import java.util.TreeSet;

public class RouteTwoNodes0401 {

    /**
     * 面试题 04.01. 节点间通路
     * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
     * 示例1:
     *  输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
     *  输出：true
     * 示例2:
     *  输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
     *  输出 true
     * 提示：
     * 节点数量n在[0, 1e5]范围内。
     * 节点编号大于等于 0 小于 n。
     * 图中可能存在自环和平行边。
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
    TreeSet<Integer>[] graphSet;
    boolean[] visited;
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        graphSet = new TreeSet[n];
        visited = new boolean[n];
        for (int i = 0; i < graph.length; i++) {
            if (graphSet[graph[i][0]] == null) {
                graphSet[graph[i][0]] = new TreeSet<>();
            }
            graphSet[graph[i][0]].add(graph[i][1]);
        }
        return dfs(start, target);
    }

    public boolean dfs(int start, int target) {
        if (start == target) {
            return true;
        }
        visited[start] = true;
        if (graphSet[start] != null) {
            for (Integer next : graphSet[start]) {
                if (!visited[next]) {
                    if (dfs(next, target)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
