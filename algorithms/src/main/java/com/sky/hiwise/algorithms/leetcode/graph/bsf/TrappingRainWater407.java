package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class TrappingRainWater407 {
    /**
     * 407. 接雨水 II
     * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
     * 示例 1:
     * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
     * 输出: 4
     * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
     */
    int R, C;
    boolean[][] visited;
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int trapRainWater(int[][] heightMap) {
        R = heightMap.length;
        C = heightMap[0].length;
        visited = new boolean[R][C];
        //Queue<int[]> queue = new LinkedList<>();
        // 优先队列中存放三元组 [x,y,h] 坐标和高度
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        // 先把最外一圈放进去
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i == 0 || j == 0 || i == R - 1 || j == C - 1) {
                    queue.add(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int d = 0; d < 4; d ++) {
                int nextX = curr[0] + dirs[d][0];
                int nextY = curr[1] + dirs[d][1];
                if (inArea(nextX, nextY) && !visited[nextX][nextY]) {
                    // 如果外围这一圈中最小的比当前这个还高，那就说明能往里面灌水啊
                    if (curr[2] > heightMap[nextX][nextY]) {
                        ans += (curr[2] - heightMap[nextX][nextY]);
                    }
                    // 如果灌水高度得是你灌水后的高度了，如果没灌水也要取高的
                    queue.add(new int[]{nextX, nextY, Math.max(curr[2], heightMap[nextX][nextY])});
                    visited[nextX][nextY] = true;
                }
            }
        }
        return ans;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
    /**
     * 接雨水I中，我们维护了左右两个最高的墙，那么在这里，就是维护周围一个圈，用堆来维护周围这一圈中的最小元素。
     * 为什么是维护最小的元素不是最大的元素呢，因为木桶原理呀。这个最小的元素从堆里弹出来，和它四个方向的元素去比较大小，看能不能往里灌水，
     * 怎么灌水呢，如果用方向就比较复杂了，我们可以用visited数组来表示哪些遍历过，哪些没遍历过。
     * 如果当前弹出来的高度比它周围的大，他就能往矮的里面灌水了，灌水后要把下一个柱子放进去的时候，
     * 放的高度要取两者较大的，也就是灌水后的高度，不是它原来矮的时候的高度了，如果不能灌水，继续走。
     */
}
