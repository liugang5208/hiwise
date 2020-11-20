package com.sky.hiwise.algorithms.leetcode.graph;

import org.omg.CORBA.PUBLIC_MEMBER;

public class DetectCyclesInGrid1559 {

    /**
     * 1559. 二维网格图中探测环
     * 给你一个二维字符网格数组 grid ，大小为 m x n ，你需要检查 grid 中是否存在 相同值 形成的环。
     * 一个环是一条开始和结束于同一个格子的长度 大于等于 4 的路径。对于一个给定的格子，你可以移动到它上、下、左、右四个方向相邻的格子之一，可以移动的前提是这两个格子有 相同的值 。
     * 同时，你也不能回到上一次移动时所在的格子。比方说，环  (1, 1) -> (1, 2) -> (1, 1) 是不合法的，因为从 (1, 2) 移动到 (1, 1) 回到了上一次移动时的格子。
     * 如果 grid 中有相同值形成的环，请你返回 true ，否则返回 false 。
     * 示例 1：
     * 输入：grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
     * 输出：true
     */
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UF uf = new UF(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && grid[i][j] == grid[i - 1][j]) {
                    if (uf.isConnected(i * n + j, (i - 1) * n + j)) {
                        return true;
                    } else {
                        uf.union(i * n + j, (i - 1) * n + j);
                    }
                }
                if (j > 0 && grid[i][j] == grid[i][j - 1]) {
                    if (uf.isConnected(i * n + j, i * n + j - 1)) {
                        return true;
                    } else {
                        uf.union(i * n + j, i * n + j - 1);
                    }
                }
            }
        }
        return false;
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

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot != qRoot) {
                parent[pRoot] = qRoot;
            }
        }
    }

}
