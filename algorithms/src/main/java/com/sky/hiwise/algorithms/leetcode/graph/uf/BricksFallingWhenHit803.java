package com.sky.hiwise.algorithms.leetcode.graph.uf;

public class BricksFallingWhenHit803 {
    /**
     * 803. 打砖块
     * 有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
     * 一块砖直接连接到网格的顶部，或者
     * 至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
     * 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。
     * 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。
     * 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
     * 示例 1：
     * 输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
     * 输出：[2]
     * 解释：
     * 网格开始为：
     * [[1,0,0,0]，
     *  [1,1,1,0]]
     * 消除 (1,0) 处加粗的砖块，得到网格：
     * [[1,0,0,0]
     *  [0,1,1,0]]
     * 两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
     * [[1,0,0,0],
     *  [0,0,0,0]]
     * 因此，结果为 [2] 。
     */
    public int R;
    public int C;
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int[] hitBricks(int[][] grid, int[][] hits) {
        R = grid.length;
        C = grid[0].length;

        // 第 1 步：把 grid 中的砖头全部击碎，通常算法问题不能修改输入数据，这一步非必需，可以认为是一种答题规范
        int[][] copy = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
              copy[i][j] = grid[i][j];
            }
        }

        // 把 copy 中的砖头全部击碎
        for (int[] hit : hits) {
            copy[hit[0]][hit[1]] = 0;
        }

        // 第 2 步：建图，把砖块和砖块的连接关系输入并查集，size 表示二维网格的大小，也表示虚拟的「屋顶」在并查集中的编号
        int size = R * C;
        UnionFind uf = new UnionFind(size + 1);
        // 将下标为 0 的这一行的砖块与「屋顶」相连
        for (int j = 0; j < C; j++) {
            if (copy[0][j] == 1) {
                uf.union(j, size);
            }
        }

        // 其余网格，如果是砖块向上、向左看一下，如果也是砖块，在并查集中进行合并
        for (int i = 1; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (copy[i][j] == 1) {
                    // 如果上方也是砖块
                    if (copy[i - 1][j] == 1) {
                        uf.union(getIndex(i, j), getIndex(i - 1, j));
                    }
                    // 如果左边也是砖块
                    if (j > 0 && copy[i][j - 1] == 1) {
                        uf.union(getIndex(i, j), getIndex(i, j - 1));
                    }
                }
            }
        }

        // 第 3 步：按照 hits 的逆序，在 copy 中补回砖块，把每一次因为补回砖块而与屋顶相连的砖块的增量记录到 res 数组中
        int hitLen = hits.length;
        int[] ans = new int[hitLen];
        for (int i = hitLen - 1; i >= 0; i--) {
            int x = hits[i][0], y = hits[i][1];
            // 注意：这里不能用 copy，语义上表示，如果原来在 grid 中，这一块是空白，这一步不会产生任何砖块掉落
            // 逆向补回的时候，与屋顶相连的砖块数量也肯定不会增加
            if (grid[x][y] == 0) {
                continue;
            }

            // 补回之前与屋顶相连的砖块数
            int origin = uf.getSize(size);
            // 注意：如果补回的这个结点在第 1 行，要告诉并查集它与屋顶相连（逻辑同第 2 步）
            if (x == 0) {
                uf.union(y, size);
            }

            // 在 4 个方向上看一下，如果相邻的 4 个方向有砖块，合并它们
            for (int d = 0; d < 4; d++) {
                int nextX = x + dirs[d][0];
                int nextY = y + dirs[d][1];
                if (inArea(nextX, nextY) && copy[nextX][nextY] == 1) {
                    uf.union(getIndex(x, y), getIndex(nextX, nextY));
                }
            }

            // 补回之后与屋顶相连的砖块数
            int current = uf.getSize(size);
            // 减去的 1 是逆向补回的砖块（正向移除的砖块），与 0 比较大小，是因为存在一种情况，添加当前砖块，不会使得与屋顶连接的砖块数更多
            ans[i] = Math.max(0, current - origin - 1);
            // 真正补上这个砖块
            copy[x][y] = 1;
        }
        return ans;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    private int getIndex(int x, int y) {
        return x * C + y;
    }

    class UnionFind {

        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                size[i] = 1;
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
                size[qRoot] += size[pRoot];
            }
        }

        /**
         * @param x
         * @return x 在并查集的根结点的子树包含的结点总数
         */
        public int getSize(int x) {
            int root = find(x);
            return size[root];
        }

    }
    /**
     * 1. 解释题意
     * 首先，题解题意很重要。不会掉落的「砖块」需要满足两个条件：
     * 「砖块」位于下标为 00 的行；
     * 与下标为 00 的行的砖块在上、下、左、右 44 个方向上相连。
     * 题目问按照数组 hits 中击落砖块的顺序，每一次有多少个砖块因为这个被击碎的砖块而消失。解题的时候须要注意以下两个条件（原因最后说）：
     * 一旦砖块掉落，它会立即从网格中消失（它不会落在其他稳定的砖块上）；
     * 所有的 (x_i, y_i)互不相同。
     * 2. 如何计算每次击碎砖块而消失的砖块的数量（关注「与屋顶相连的砖块的总数变化」）
     * 和顶部相连的砖块不会掉落，击碎了一个砖块以后，可能会使得其它与 被击碎砖块 连接的砖块不再与顶部相连，然后它们消失。
     * 「击碎砖块之前」与「击碎砖块之后」与 屋顶 相连的砖块数的差值，再减 11 就是因为这一次操作而消失的砖块数。如下图所示：
     * 3. 如何想到并查集
     * 当前问题是一个图的连通性问题，砖块和砖块如果在 44 个方向上相邻，表示这两个砖块上有一条边。砖块的相邻关系而产生的连接关系具有传递性；
     * 第 00 行的砖块连接着「屋顶」；
     * 击碎了一个砖块以后，可能会使得其它与「被击碎砖块」 连接 的砖块不再与顶部相连，然后它们消失；
     * 题目只问结果，没有问具体连接的情况；
     * 连通的砖块个数是我们所关心的，「并查集」内部可以维护「以当前结点为根结点的子树的结点总数」。
     * 4. 如何使用并查集
     * 消除一个砖块的效果是：一个连通分量被分成了两个连通分量；
     * 并查集的作用是：把两个连通分量合并成一个连通分量。
     * 提示我们这个问题需要 反向 思考。即考虑：补上被击碎的砖块以后，有多少个砖块因为这个补上的这个砖块而与屋顶的砖块相连。每一次击碎一个砖块，因击碎砖块而消失的砖块只会越来越少。因此可以按照数组 hits 的顺序 逆序地 把这些砖块依次补上。如图所示：
     * 当最后一块砖块补上的时候，就恰好可以恢复成刚开始的时候整个二维表格的样子。
     * 5. 解题步骤
     * 根据数组 hits，将输入的表格 grid 里的对应位置全部设置为 00 ，这是因为我们要逆序补全出整个初始化的时候二维表格的砖块；
     * 从最后一个击碎的砖块开始，计算补上这个被击碎的砖块的时候，有多少个砖块因为这个补上的砖块而与屋顶相连，这个数目就是按照题目中的描述，击碎砖块以后掉落的砖块的数量。
     * 下面我们介绍实现细节。
     * 6. 实现细节
     * 在并查集中设置一个特殊的结点，表示「屋顶」；
     * 逆序补回的时候，由于补回，增加的连接到「屋顶」的砖块数应该这样算：res[i] = Math.max(0, current - origin - 1); （current 和 origin 的含义请见代码）。因为有可能补回一个砖块前后，连接到「屋顶」的砖块总数没有变化，如下图所示：
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/bricks-falling-when-hit/solution/803-da-zhuan-kuai-by-leetcode-r5kf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
