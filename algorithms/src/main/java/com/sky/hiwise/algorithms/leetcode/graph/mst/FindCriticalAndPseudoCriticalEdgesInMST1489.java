package com.sky.hiwise.algorithms.leetcode.graph.mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindCriticalAndPseudoCriticalEdgesInMST1489 {
    /**
     * 1489. 找到最小生成树里的关键边和伪关键边
     * 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，
     * 其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。
     * 最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
     * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。
     * 伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
     * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
     * 示例 1：
     * 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
     * 输出：[[0,1],[2,3,4,5]]
     * 解释：上图描述了给定图。
     * 下图是所有的最小生成树。
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                newEdges[i][j] = edges[i][j];
            }
            newEdges[i][3] = i;
        }

        Arrays.sort(newEdges, Comparator.comparingInt(a -> a[2]));
        UnionFind uf = new UnionFind(n);
        int value = 0;
        for (int i = 0; i < m; i++) {
            if (uf.union(newEdges[i][0], newEdges[i][1])) {
                value += newEdges[i][2];
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            ans.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++){
            UnionFind uf1 = new UnionFind(n);
            //判读是否为关键边
            //如果最小生成树中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。
            //也就是说，如果设原图最小生成树的权值为value，那么去掉这条边后：
            //要么整个图不连通，不存在最小生成树
            //要么整个图联通，对应的最小生成树的权值为v，其严格大于value
            int v = 0;
            for (int j = 0; j < m; j++) {
                if (i != j && uf1.union(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }
            if (uf1.count != 1 || (uf1.count == 1 && v > value)) {
                ans.get(0).add(newEdges[i][3]);
                continue;
            }
            //判断是否为伪关键边
            //可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
            //也就是说，我们可以在计算最小生成树的过程中，最先考虑这条边，即最先将这条边的两个端点在并查集中合并。
            //设最终得到的最小生成树权值为v，如果v=value，那么这条边就是伪关键边
            uf1 = new UnionFind(n);
            uf1.union(newEdges[i][0], newEdges[i][1]);
            v = newEdges[i][2];
            for (int j = 0; j < m; j++) {
                if (i != j && uf1.union(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }
            if (v == value) {
                ans.get(1).add(newEdges[i][3]);
            }
        }
        return ans;
    }

    /**
     * 本篇题解中会给出两种算法，并且每种算法都默认读者已经掌握了对应的知识点：
     * 方法一只需要枚举每一条边，并用略微修改的 \texttt{Kruskal}Kruskal 算法判断其是否是关键边或伪关键边；
     * 方法二利用了 \texttt{Kruskal}Kruskal 算法的连通性性质，以及无向图找桥边的 \texttt{Tarjan}Tarjan 算法，即使在竞赛中也不算容易，仅供读者挑战自我。
     * 方法一：枚举 + 最小生成树判定
     * 思路与算法
     * 我们首先需要理解题目描述中对于「关键边」和「伪关键边」的定义：
     * 关键边：如果最小生成树中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。也就是说，如果设原图最小生成树的权值为 \textit{value}value，那么去掉这条边后：
     * 要么整个图不连通，不存在最小生成树；
     * 要么整个图联通，对应的最小生成树的权值为 vv，其严格大于 \textit{value}value。
     * 伪关键边：可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。也就是说，我们可以在计算最小生成树的过程中，最先考虑这条边，即最先将这条边的两个端点在并查集中合并。
     * 设最终得到的最小生成树权值为 vv，如果 v = \textit{value}v=value，那么这条边就是伪关键边。
     * 需要注意的是，关键边也满足伪关键边对应的性质。因此，我们首先对原图执行 \texttt{Kruskal}Kruskal 算法，
     * 得到最小生成树的权值 \textit{value}value，随后我们枚举每一条边，首先根据上面的方法判断其是否是关键边，如果不是关键边，再判断其是否是伪关键边。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/solution/zhao-dao-zui-xiao-sheng-cheng-shu-li-de-gu57q/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class UnionFind {
        private int[] parent;
        public int count;
        public  UnionFind(int n) {
            parent = new int[n];
            count = n;
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

        public boolean union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) {
                return false;
            }
            parent[pRoot] = qRoot;
            count--;
            return true;
        }
    }
}
