package com.sky.hiwise.algorithms.leetcode.graph.mst;

import javax.swing.plaf.PanelUI;
import java.util.*;

public class MinCostConnectAllPoints1584 {

    /**
     * 1584. 连接所有点的最小费用
     * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
     * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
     * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
     * 示例 1：
     * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
     * 输出：20
     * 解释：
     * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
     * 注意到任意两个点之间只有唯一一条路径互相到达。
     */
    //Kruskal 算法
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                list.add(new Edge(i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }
        Collections.sort(list);

        UF uf = new UF(n);
        int res = 0;
        for (Edge edge : list) {
            int p = edge.x;
            int q = edge.y;
            if (!uf.isConnected(p, q)) {
                uf.unionElement(p, q);
                res += edge.dist;
            }
        }
        return res;
    }


    public int minCostConnectPoints1(int[][] points) {
        int n = points.length;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pq.add(new Edge(i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }

        UF uf = new UF(n);
        int res = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int p = edge.x;
            int q = edge.y;
            if (!uf.isConnected(p, q)) {
                uf.unionElement(p, q);
                res += edge.dist;
            }
        }
        return res;
    }

    //Prim 算法
    public int minCostConnectPoints2(int[][] points) {
        int n = points.length;
        if (n <= 1) {
            return 0;
        }
        boolean[] visited = new boolean[n];
        visited[0] = true;
        TreeMap<Integer, Edge>[] graph = new TreeMap[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph[i] == null) {
                    graph[i] = new TreeMap<>();
                }
                if (graph[j] == null) {
                    graph[j] = new TreeMap<>();
                }
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                graph[i].put(j, new Edge(i, j, dist));
                graph[j].put(i, new Edge(j, i, dist));
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Map.Entry entry : graph[0].entrySet()) {
            pq.add((Edge) entry.getValue());
        }
        int res = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (visited[edge.y]) {
                continue;
            }
            res += edge.dist;
            int newx = edge.y;
            visited[newx] = true;
            for(Map.Entry entry : graph[newx].entrySet()) {
                int w = (int) entry.getKey();
                if (visited[w]) {
                    continue;
                }
                pq.add((Edge) entry.getValue());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}};
        System.out.println((new MinCostConnectAllPoints1584()).minCostConnectPoints2(points));
    }


    class UF {
        private int[] parent;
        public UF(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int p) {
            if (p != parent[p]) {
                parent[p] = find(parent[p]);
            }
            return parent[p];
        }

        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        public void unionElement(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) {
                return ;
            }
            parent[pRoot] = qRoot;
        }
    }

    class Edge implements Comparable<Edge>{
        public int x;
        public int y;
        public int dist;

        public Edge(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;

        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
    /**
     * Kruskal 算法
     * 此算法可以称为“加边法”，初始最小生成树边数为0，每迭代一次就选择一条满足条件的最小代价边，加入到最小生成树的边集合里。
     * 把图中的所有边按代价从小到大排序；
     * 把图中的n个顶点看成独立的n棵树组成的森林；
     * 按权值从小到大选择边，所选的边连接的两个顶点 ui, vi;
     * ui, vi应属于两颗不同的树（一棵树上的两条个顶点相连的话就形成环了），则成为最小生成树的一条边，并将这两颗树合并作为一颗树。
     * 重复(3),直到所有顶点都在一颗树内或者有n-1条边为止。
     * 作者：yizhe-shi
     * 链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points/solution/c-kruskalprimsuan-fa-jie-da-by-yizhe-shi/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
