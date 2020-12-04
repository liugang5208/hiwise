package com.sky.hiwise.algorithms.leetcode.graph.dfs;

public class CheckValidPath1391 {
    /**
     * 1391. 检查网格中是否存在有效路径
     * 给你一个 m x n 的网格 grid。网格里的每个单元都代表一条街道。grid[i][j] 的街道可以是：
     * 1 表示连接左单元格和右单元格的街道。
     * 2 表示连接上单元格和下单元格的街道。
     * 3 表示连接左单元格和下单元格的街道。
     * 4 表示连接右单元格和下单元格的街道。
     * 5 表示连接左单元格和上单元格的街道。
     * 6 表示连接右单元格和上单元格的街道。
     * 你最开始从左上角的单元格 (0,0) 开始出发，网格中的「有效路径」是指从左上方的单元格 (0,0) 开始、一直到右下方的 (m-1,n-1) 结束的路径。该路径必须只沿着街道走。
     * 注意：你 不能 变更街道。
     * 如果网格中存在有效的路径，则返回 true，否则返回 false 。
     * 示例 1：
     * 输入：grid = [[2,4,3],[6,5,2]]
     * 输出：true
     * 解释：如图所示，你可以从 (0, 0) 开始，访问网格中的所有单元格并到达 (m - 1, n - 1) 。
     */
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0},  {0, -1}};
    int R, C;
    boolean[][] visited;
    int[][] pipe = new int[][]{
            {-1,-1,-1,-1},
            {-1,1,-1,3},
            {0,-1,2,-1},
            {-1,0,3,-1},
            {-1,-1,1,0},
            {3,2,-1,-1},
            {1,-1,-1,2}};
    public boolean hasValidPath(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        visited = new boolean[R][C];
        int first = grid[0][0];
        for (int i = 0; i < 4; i++) {
            if (pipe[first][i] != -1) {
                if (dfs(grid, pipe[first][i], 0, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 解题思路：
     * 通过构建pipe数组，将每个拼图转化为四个方向上的移动限制图。
     * 例：
     * pipe[3][2]=3，代表3号拼图可以由向上的方向进入其中，并转向左方向继续前进。
     * pipe[5][3]=-1，代表5号拼图不可以由向左的方向进入其中。
     * 其中0代表向下、1代表向右、2代表向上、3代表向左、-1代表不可走
     * 作者：wu-xing-que-ni-2
     * 链接：https://leetcode-cn.com/problems/check-if-there-is-a-valid-path-in-a-grid/solution/cdfsjie-fa-rong-yi-li-jie-dai-ma-duan-zhu-shi-duo-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    private boolean dfs(int[][] grid, int dir, int x, int y) {
        System.out.println("dir:" + dir);
        if (x == R - 1 && y == C - 1) {
            return true;
        }
        visited[x][y] = true;
        int nextX = x + dirs[dir][0], nextY = y + dirs[dir][1];
        if (!inArea(nextX, nextY)) {
            return false;
        }
        if (!visited[nextX][nextY] && pipe[grid[nextX][nextY]][dir] != -1) {
            return dfs(grid, pipe[grid[nextX][nextY]][dir], nextX, nextY);
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{2,4,3},{6,5,2}};
        int[][] grid1 = new int[][]{{4,1},{6,1}};
        System.out.println((new CheckValidPath1391()).hasValidPath(grid1));
    }
}
