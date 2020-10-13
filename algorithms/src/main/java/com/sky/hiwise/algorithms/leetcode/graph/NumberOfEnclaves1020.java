package com.sky.hiwise.algorithms.leetcode.graph;

public class NumberOfEnclaves1020 {

    /**
     * 1020. 飞地的数量
     * 给出一个二维数组 A，每个单元格为 0（代表海）或 1（代表陆地）。
     * 移动是指在陆地上从一个地方走到另一个地方（朝四个方向之一）或离开网格的边界。
     * 返回网格中无法在任意次数的移动中离开网格边界的陆地单元格的数量。
     * 示例 1：
     * 输入：[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
     * 输出：3
     * 解释：
     * 有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
     * 示例 2：
     * 输入：[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
     * 输出：0
     * 解释：
     * 所有 1 都在边界上或可以到达边界。
     * 提示：
     * 1 <= A.length <= 500
     * 1 <= A[i].length <= 500
     * 0 <= A[i][j] <= 1
     * 所有行的大小都相同
     * @param A
     * @return
     */
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    boolean[][] visited;
    int R, C;
    int[][] A;
    public int numEnclaves(int[][] A) {
        if (A == null) {
            return 0;
        }
        R = A.length;
        if (R == 0) {
            return 0;
        }
        C = A[0].length;
        if (C == 0) {
            return 0;
        }
        this.A = A;
        visited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                boolean isEdge = (r == 0 || r == R - 1 || c == 0 || c == C -1);
                if (!visited[r][c] && isEdge && A[r][c] == 1) {
                    dfs(r, c);
                }
            }
        }

        int count = 0;
        for (int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if (A[r][c] == 1) {
                    count ++;
                }
            }
        }

        return count;
    }

    private void dfs(int r, int c) {
        visited[r][c] = true;
        A[r][c] = 0;
        for (int d = 0; d < 4; d++) {
            int newR = r + dirs[d][0];
            int newC = c + dirs[d][1];
            if (inArea(newR, newC) && !visited[newR][newC] && A[newR][newC] == 1) {
                dfs(newR, newC);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        int[][] A = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        System.out.println((new NumberOfEnclaves1020()).numEnclaves(A));
    }
}
