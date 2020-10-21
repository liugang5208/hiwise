package com.sky.hiwise.algorithms.leetcode.graph.dfs;

public class RedundantConnection685 {
    /**
     * 685. 冗余连接 II
     * 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。
     * 输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，
     * 这条附加的边不属于树中已存在的边。
     * 结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
     * 返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
     * 示例 1:
     * 输入: [[1,2], [1,3], [2,3]]
     * 输出: [2,3]
     * 解释: 给定的有向图如下:
     *   1
     *  / \
     * v   v
     * 2-->3
     * 示例 2:
     * 输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
     * 输出: [4,1]
     * 解释: 给定的有向图如下:
     * 5 <- 1 -> 2
     *      ^    |
     *      |    v
     *      4 <- 3
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int len = edges.length;
        int[] indegree = new int[len + 1];
        for (int[] edge : edges) {
            indegree[edge[1]]++;
        }
        //先尝试删除构成入度为 2 的边，看看是否形成环
        for (int i = len - 1; i >= 0; i--) {
            if (indegree[edges[i][1]] == 2) {
                //如果不构成环，这条边就是要去掉的那条边
                if (!hasCycle(edges, len, i)) {
                    return edges[i];
                }
            }
        }

        //再尝试删除构成入度为 1 的边，看看是否形成环
        for (int i = len - 1; i >= 0; i--) {
            if (indegree[edges[i][1]] == 1) {
                // 如果不构成环，这条边就是要去掉的那条边
                if (!hasCycle(edges, len, i)) {
                    return edges[i];
                }
            }
        }
        return new int[0];
    }

    public boolean hasCycle(int[][] edges, int len, int removeIdx) {
        UnionFind uf = new UnionFind(len + 1);
        for (int i = 0; i < len; i++) {
            if (i == removeIdx) {
                continue;
            }
            if (!uf.union(edges[i][0], edges[i][1])) {
                // 合并失败，表示 edges[i][0] 和 edges[i][1] 在一个连通分量里，即构成了环
                return true;
            }
        }
        return false;
    }

    /**
     * 这个问题与第 684 题的区别是：
     * 第 684 题基于无向图，在无向图中判断是否有环，很容易想到可以使用 并查集；
     * 第 685 题基于 有向图，在有向图中判断是是否有环，需要使用拓扑排序（「力扣」第 207 题、第 210 题，思想：贪心算法、BFS，概念：结点的度）。
     * 当前这个问题（第 685 题）需要我们返回多余的一条边。拓扑排序主要回答拓扑序，顺便回答了图中是否有环，对于这个问题来说，使用拓扑排序找到多余的一条边是相对麻烦的。
     * 但是拓扑排序中的重要概念 结点的入度 可以帮助我们解决这个问题。
     * 由此，我们可以归纳出，有根树的特点：
     * 只有唯一的一个入度为 00 的结点，它是根结点；
     * 不是根结点的其它所有的结点入度为 11；
     * 不可能存在入度为 22 的结点。
     * 根据示例 1 ，我们知道，不能有入度为 22 的结点；
     * 根据示例 2 ，我们知道，在不能有入度为 22 的结点的前提下，不能形成回路。
     * 为此设计算法如下：
     * 先统计每一个结点的入度，如果有入度为 22 的结点，考虑删除一条边（根据题目意思，删除的是输入的边的列表中最后出现的），剩下的 有向边 是否形成回路（形成环）。如果不能形成环，就应该删除这条边；
     * 在没有如果有入度为 22 的结点的前提下，尝试删除形成入度为 11 的 有向边 （不能删除入度为 00 的有向边），判断剩下的 有向边 是否形成环。
     * 说明：在没有入度为 22 的结点的情况下（结合示例 2 来理解），判断有向图是否形成回路，可以把有向图当成无向图来看，因此可以使用并查集。
     * 编码说明：
     * 这个问题里，题目输入的边的条数等于结点的个数，因为就是刚刚好多了 11 条边，题目才让我们删，33 个顶点的有根树只可能有 22 条边，注意代码中 +1+1 是因为从 11 开始计算；
     * 题目要求我们，有多个结果的时候，返回 edges 里最后出现的边，因此 从后向前遍历，删除某条边的意思是：不把它加入并查集。
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/redundant-connection-ii/solution/bing-cha-ji-java-by-liweiwei1419/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public class UnionFind {
        private int[] parent;
        public UnionFind(int len) {
            parent = new int[len];
            for (int i = 0; i < len; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public boolean union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return false;
            }
            parent[rootP] = rootQ;
            return true;
        }
    }
}
