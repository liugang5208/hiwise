package com.sky.hiwise.algorithms.leetcode.graph.uf;

public class RegionsCutBySlashes959 {
    /**
     * 959. 由斜杠划分区域
     * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
     * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
     * 返回区域的数目。
     * 示例 1：
     * 输入：
     * [
     *   " /",
     *   "/ "
     * ]
     * 输出：2
     * 解释：2x2 网格如下：
     * 示例 2：
     * 输入：
     * [
     *   " /",
     *   "  "
     * ]
     * 输出：1
     * 解释：2x2 网格如下：
     */
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int size = n * n * 4;
        UnionFind uf = new UnionFind(size);
        for (int i = 0; i < n; i++) {
            char[] rows = grid[i].toCharArray();
            for (int j = 0; j < n; j++) {
                int index = 4 * (i * n + j);
                char row = rows[j];
                if (row == '/') {
                    uf.union(index, index + 3);
                    uf.union(index + 1, index + 2);
                } else if (row == '\\') {
                    uf.union(index, index + 1);
                    uf.union(index + 3, index + 2);
                } else {
                    uf.union(index, index + 1);
                    uf.union(index + 1, index + 2);
                    uf.union(index + 2, index + 3);
                }

                if (j + 1 < n) {
                    uf.union(index + 1, 4 * (i * n + j + 1) + 3);
                }

                if (i + 1 < n) {
                    uf.union(index + 2, 4 * ((i + 1) * n + j));
                }
            }
        }
        return uf.getCount();
    }

    class UnionFind {

        private int[] parent;
        private int count;
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

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot != qRoot) {
                parent[pRoot] = qRoot;
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }
}
