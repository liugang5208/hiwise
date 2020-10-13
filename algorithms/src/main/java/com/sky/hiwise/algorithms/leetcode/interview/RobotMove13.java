package com.sky.hiwise.algorithms.leetcode.interview;

import java.util.LinkedList;
import java.util.Queue;

public class RobotMove13 {

    /**
     * 面试题13. 机器人的运动范围
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
     * 也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
     * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     * 示例 1：
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     * 示例 1：
     * 输入：m = 3, n = 1, k = 0
     * 输出：1
     */
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean[][] visited;
    private int R, C;
    public int movingCount(int m, int n, int k) {
        R = m;
        C = n;
        visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        int sum = 1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nextX = curr[0] + dirs[d][0];
                int nextY = curr[1] + dirs[d][1];
                if (inArea(nextX, nextY) && !visited[nextX][nextY] && count(nextX, nextY) <= k) {
                    sum++;
                    visited[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
        return sum;
    }

    private int count(int m, int n) {
        int sum = 0;
        while (m != 0) {
            sum += m % 10;
            m = m / 10;
        }

        while (n != 0) {
            sum += n % 10;
            n = n / 10;
        }
        return sum;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}
