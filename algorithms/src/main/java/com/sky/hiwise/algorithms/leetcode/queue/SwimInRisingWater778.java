package com.sky.hiwise.algorithms.leetcode.queue;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SwimInRisingWater778 {
    /**
     * 778. 水位上升的泳池中游泳
     * 在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
     * 现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，
     * 但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。
     * 当然，在你游泳的时候你必须待在坐标方格里面。
     * 你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？
     * 示例 1:
     * 输入: [[0,2],[1,3]]
     * 输出: 3
     * 解释:
     * 时间为0时，你位于坐标方格的位置为 (0, 0)。
     * 此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
     * 等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
     * 示例2:
     * 输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
     * 输出: 16
     * 解释:
     *  0  1  2  3  4
     * 24 23 22 21  5
     * 12 13 14 15 16
     * 11 17 18 19 20
     * 10  9  8  7  6
     * 最终的路线用加粗进行了标记。
     * 我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
     * 提示:
     * 2 <= N <= 50.
     * grid[i][j] 是 [0, ..., N*N - 1] 的排列。
     */
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int R, C;
    public int swimInWater(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> grid[a / C][a % C]));
        pq.offer(0);
        int ans = 0;
        visited.add(0);
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            int r = curr / C, c = curr % C;
            ans = Math.max(ans, grid[r][c]);
            if (r == R - 1 && c == C - 1) {
                return ans;
            }
            for (int d = 0; d < 4; d++) {
                int nextR = r + dirs[d][0], nextC = c + dirs[d][1];
                int next = nextR * C + nextC;
                if (inArea(nextR, nextC) && !visited.contains(next)) {
                    pq.add(next);
                    visited.add(next);
                }
            }
        }
        return -1;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,2},{1,3}};
        System.out.println((new SwimInRisingWater778()).swimInWater(grid));
    }


    /**
     * 方法一： 堆【通过】
     * 思路和算法
     *
     * 用优先队列保存下一步可以游向的平台，每次都选择高度最小的平台。以这种方式到达终点时，路径中遇到的最高平台就是答案。
     */
}
