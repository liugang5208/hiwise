package com.sky.hiwise.algorithms.leetcode.graph.uf;

import java.util.HashSet;
import java.util.Set;

public class MostStonesRemoved947 {
    /**
     * 947. 移除最多的同行或同列石头
     * 我们将石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
     * 每次 move 操作都会移除一块所在行或者列上有其他石头存在的石头。
     * 请你设计一个算法，计算最多能执行多少次 move 操作？
     * 示例 1：
     * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
     * 输出：5
     * 示例 2：
     * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
     * 输出：3
     * 1 <= stones.length <= 1000
     * 0 <= stones[i][j] < 10000
     */
    public int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind uf = new UnionFind(20000);
        for (int[] stone : stones) {
            uf.union(stone[0], stone[1] + 10000);
        }

        Set<Integer> set = new HashSet<>();
        for (int[] stone : stones) {
            set.add(uf.find(stone[0]));
        }
        return n - set.size();
    }
    /**
     * 方法二： 并查集
     * 思路
     * 在方法一中，我们通过深度优先搜索来计算隐式图中连通分量的个数。实际上有更高效的解决方法，那就是用并查集。
     * 算法
     * 对于一个坐标为 (i, j) 的石子来说，需要把行 i 和列 j 合并，因为并查集是一维的，用 j+10000 来代替 j。
     * 在将所有石子的行和列都合并好之后，只需数一下并查集中有几个集合就可以得到答案了。
     * 简洁起见，这里实现的并查集就不根据 rank 来合并了。因此渐进复杂度会比用 rank 的高一点。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/solution/yi-chu-zui-duo-de-tong-xing-huo-tong-lie-shi-tou-b/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
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

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot != qRoot) {
                parent[pRoot] = qRoot;
            }
        }
    }
}
