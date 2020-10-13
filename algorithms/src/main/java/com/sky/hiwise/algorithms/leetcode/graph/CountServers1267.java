package com.sky.hiwise.algorithms.leetcode.graph;

public class CountServers1267 {
    /**
     * 1267. 统计参与通信的服务器
     * 这里有一幅服务器分布图，服务器的位置标识在 m * n 的整数矩阵网格 grid 中，1 表示单元格上有服务器，0 表示没有。
     * 如果两台服务器位于同一行或者同一列，我们就认为它们之间可以进行通信。
     * 请你统计并返回能够与至少一台其他服务器进行通信的服务器的数量。
     */

    public int countServers(int[][] grid) {
        int R = grid.length, C = grid[0].length;
        int ans = 0;
        int[] countR = new int[R];
        int[] countC = new int[C];
        for (int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if (grid[r][c] == 1) {
                    countR[r] ++;
                    countC[c] ++;
                }
            }
        }
        for (int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                //当前rc = 1 且存在另一个 所以 countR[r] > 1 而不能=
                if (grid[r][c] == 1 && (countR[r] > 1 || countC[c] > 1)) {
                    ans++;
                }
            }
        }
        return  ans;
    }




    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 0}, {0, 1}};
        System.out.println((new CountServers1267()).countServers(grid));
    }
}
