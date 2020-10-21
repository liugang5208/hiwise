package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.*;

public class MinHeightTrees310 {
    /**
     * 310. 最小高度树
     * 对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。
     * 给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。
     * 格式
     * 该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表（每一个边都是一对标签）。
     * 你可以假设没有重复的边会出现在 edges 中。由于所有的边都是无向边， [0, 1]和 [1, 0] 是相同的，因此不会同时出现在 edges 里。
     * 示例 1:
     * 输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
     *
     *         0
     *         |
     *         1
     *        / \
     *       2   3
     * 输出: [1]
     * 示例 2:
     * 输入: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
     *      0  1  2
     *       \ | /
     *         3
     *         |
     *         4
     *         |
     *         5
     * 输出: [3, 4]
     * 说明:
     *  根据树的定义，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
     * 树的高度是指根节点和叶子节点之间最长向下路径上边的数量。
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return  res;
        }
        TreeSet<Integer>[] graph = new TreeSet[edges.length];
        int[] outdegree = new int[n];

        for (int[] edge : edges) {
            if (graph[edge[0]] == null) {
                graph[edge[0]] = new TreeSet<>();
            }
            if (graph[edge[1]] == null) {
                graph[edge[1]] = new TreeSet<>();
            }
            graph[edge[1]].add(edge[0]);
            graph[edge[0]].add(edge[1]);
            outdegree[edge[0]]++;
            outdegree[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (outdegree[i] == 1) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            res = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                res.add(curr);
                for (int next : graph[curr]) {
                    outdegree[next]--;
                    if (outdegree[next] == 1) {
                        queue.add(next);
                    }
                }
            }
        }

        return res;
    }
    /**
     * BFS 超级简单 注释超级详细
     * 简单分析过程：
     * 首先，我们看了样例，发现这个树并不是二叉树，是多叉树。
     * 然后，我们可能想到的解法是：根据题目的意思，就挨个节点遍历bfs，统计下每个节点的高度，然后用map存储起来，后面查询这个高度的集合里最小的就可以了。
     * 但是这样会超时的。
     * 于是我们看图（题目介绍里面的图）分析一下，发现，越是靠里面的节点越有可能是最小高度树。
     * 所以，我们可以这样想，我们可以倒着来。
     * 我们从边缘开始，先找到所有出度为1的节点，然后把所有出度为1的节点进队列，然后不断地bfs，
     * 最后找到的就是两边同时向中间靠近的节点，那么这个中间节点就相当于把整个距离二分了，那么它当然就是到两边距离最小的点啦，也就是到其他叶子节点最近的节点了。
     * 作者：xiao-xin-28
     * 链接：https://leetcode-cn.com/problems/minimum-height-trees/solution/zui-rong-yi-li-jie-de-bfsfen-xi-jian-dan-zhu-shi-x/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
