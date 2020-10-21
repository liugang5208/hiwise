package com.sky.hiwise.algorithms.leetcode.graph.dfs;

public class RedundantConnection684 {
    /**
     * 684. 冗余连接
     * 在本问题中, 树指的是一个连通且无环的无向图。
     * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
     * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
     * 示例 1：
     * 输入: [[1,2], [1,3], [2,3]]
     * 输出: [2,3]
     * 解释: 给定的无向图为:
     *   1
     *  / \
     * 2 - 3
     * 示例 2：
     * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
     * 输出: [1,4]
     * 解释: 给定的无向图为:
     * 5 - 1 - 2
     *     |   |
     *     4 - 3
     * 输入的二维数组大小在 3 到 1000。
     * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
     * @param edges
     * @return
     */
    int[] uf;
    public int[] findRedundantConnection(int[][] edges) {
        uf = new int[1001];
        for(int i = 0; i < edges.length; i++) {
            uf[i] = i;
        }
        for (int j = 0; j < edges.length; j++) {
            int set1 = find(edges[j][0]);
            int set2 = find(edges[j][1]);
            if (set1 == set2) {
                return edges[j];
            } else {
                uf[set1] = set2;
            }
        }
        return new int[]{0, 0};
    }

    public int find(int idx) {
        int num = idx;
        while (uf[num] != num) {
            num = uf[num];
        }
        return num;
    }

    /**
     * 算法：
     * 如果我们熟悉并查集（DSU）数据结构，我们可以直接使用它来解决这个问题：我们只需找到已经连接的图中出现的第一条边。
     * 本解释的其余部分将重点介绍实现 DSU 的细节。
     * 一个 DSU 数据结构可以用来维护图形连接组件的数据，并快速查询它们。有两种操作：
     * dsu.find(node x)，找到元素 x 所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合。
     * dsu.union(node x, node y)，把元素 x 和元素 y 所在的集合合并，要求 x和 y 所在的集合不相交，如果相交则不合并。
     * 为了实现这一点，我们跟踪父结点，它会记录同一连接节点中较小结点的所在的集合。如果结点是它自己的父结点，我们将其称为连接结点的领导者。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/redundant-connection/solution/rong-yu-lian-jie-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
