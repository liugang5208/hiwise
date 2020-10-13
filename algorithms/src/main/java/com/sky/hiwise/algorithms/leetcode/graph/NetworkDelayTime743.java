package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.*;

public class NetworkDelayTime743 {

    /**
     * 743. 网络延迟时间
     * 有 N 个网络节点，标记为 1 到 N。
     *
     * 给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间。
     *
     * 现在，我们从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。
     * 示例：
     * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
     * 输出：2
     */
    public Map<Integer, List<int[]>> graph = new HashMap<>();
    public int[] visited;
    public int networkDelayTime(int[][] times, int N, int K) {
        for (int[] time : times) {
            if (!graph.containsKey(time[0])) {
                graph.put(time[0], new ArrayList<>());
            }
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }
        for (int node : graph.keySet()) {
            Collections.sort(graph.get(node), (a, b) -> a[1] - b[1]);
        }
        visited  = new int[N + 1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        dfs(K, 0);
        int ans = -1;
        for (int i = 1; i<= N; i++) {
            if (visited[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, visited[i]);
        }
        return ans;
    }

    public void dfs(int index, int dist) {
        if (dist >= visited[index]) {
            return;
        }
        visited[index] = dist;
        if (graph.containsKey(index)) {
            for (int[] info : graph.get(index)) {
                dfs(info[0], dist + info[1]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] times = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        System.out.println((new NetworkDelayTime743()).networkDelayTime(times, 4, 2));

    }
}
