package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.PriorityQueue;
import java.util.TreeMap;

public class PathWithMaxProbability1514 {

    /**
     * 1514. 概率最大的路径
     * 给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，
     * 其中 edges[i] = [a, b] 表示连接节点 a 和 b 的一条无向边，且该边遍历成功的概率为 succProb[i] 。
     * 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。
     * 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。
     * 示例 1：
     * 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
     * 输出：0.25000
     * 解释：从起点到终点有两条路径，其中一条的成功概率为 0.2 ，而另一条为 0.5 * 0.5 = 0.25
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        TreeMap<Integer, Double>[] graph = new TreeMap[n];
        for (int i = 0; i < edges.length; i++) {
            if (graph[edges[i][0]] == null) {
                graph[edges[i][0]] = new TreeMap<>();
            }
            if (graph[edges[i][1]] == null) {
                graph[edges[i][1]] = new TreeMap<>();
            }
            graph[edges[i][0]].put(edges[i][1], succProb[i]);
            graph[edges[i][1]].put(edges[i][0], succProb[i]);
        }

        double[] prob = new double[n];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 1.0));
        prob[start] = 1;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.dis < prob[curr.source]) {
                continue;
            }
            if (graph[curr.source] != null) {
                for (Integer nextNode : graph[curr.source].keySet()) {
                    double nextProb = graph[curr.source].get(nextNode);
                    if (prob[nextNode] < prob[curr.source] * nextProb) {
                        prob[nextNode] = prob[curr.source] * nextProb;
                        pq.add(new Node(nextNode, prob[nextNode]));
                    }
                }
            }

        }
        return prob[end];
    }

    public static void main(String[] args) {

        int n = 5;
        int [][] edges = new int[][]{{1,4},{2,4},{0,4},{0,3},{0,2},{2,3}};
        double[] succProb = new double[]{0.37,0.17,0.93,0.23,0.39,0.04};
        int start = 3, end = 4;

        /**
         * 5
         * [[1,4],[2,4],[0,4],[0,3],[0,2],[2,3]]
         * [0.37,0.17,0.93,0.23,0.39,0.04]
         * 3
         * 4
         */

        System.out.println((new PathWithMaxProbability1514()).maxProbability(n, edges, succProb, start, end));

    }

    private class Node implements Comparable<Node> {

        public Integer source;

        public double dis;

        public Node(Integer source, double dis) {
            this.source = source;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            if (this.dis == o.dis) {
                return this.source - o.source;
            }
            return this.dis - o.dis > 0 ? -1 : 1;
        }
    }

}
