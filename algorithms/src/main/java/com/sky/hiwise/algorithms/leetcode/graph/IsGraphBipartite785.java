package com.sky.hiwise.algorithms.leetcode.graph;

public class IsGraphBipartite785 {



    /**
     * 785. 判断二分图
     * 给定一个无向图graph，当这个图为二分图时返回true。
     * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，
     * 并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
     * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。
     * 每个节点都是一个在0到graph.length-1之间的整数。
     * 这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
     * 示例 1:
     * 输入: [[1,3], [0,2], [1,3], [0,2]]
     * 输出: true
     * 解释:
     * 无向图如下:
     * 0----1
     * |    |
     * |    |
     * 3----2
     * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
     * @param graph
     * @return
     */
    private int[] colors;
    private boolean[] visited;
    private int[][] graph;
    public boolean isBipartite(int[][] graph) {
        int V = graph.length;
        colors = new int[V];
        visited = new boolean[V];
        this.graph = graph;
        for(int v = 0; v < V; v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for(int w : graph[v]) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else if (colors[w] == colors[v]) {
                return false;
            }
        }
        return true;
    }
}
