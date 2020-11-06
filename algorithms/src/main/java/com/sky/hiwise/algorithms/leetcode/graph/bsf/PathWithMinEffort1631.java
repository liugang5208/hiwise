package com.sky.hiwise.algorithms.leetcode.graph.bsf;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathWithMinEffort1631 {
    /**
     * 1631. 最小体力消耗路径
     * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。
     * 一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
     * 你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
     * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
     * 请你返回从左上角走到右下角的最小 体力消耗值 。
     * 示例 1：
     * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
     * 输出：2
     * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
     * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路劲差值最大值为 3 。
     */
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int R, C;
    public int minimumEffortPath(int[][] heights) {
        R = heights.length;
        C = heights[0].length;
        boolean[][] visited = new boolean[R][C];
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, 0, 0));
        int[] ans = new int[R * C];
        Arrays.fill(ans, Integer.MAX_VALUE);
        ans[0] = 0;
        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            if (visited[currNode.nodeX][currNode.nodeY]) {
                continue;
            }
            int dist = currNode.dist;
            ans[currNode.nodeX * C + currNode.nodeY] = dist;
            visited[currNode.nodeX][currNode.nodeY] = true;
            for (int[] d : dirs) {
                int nextX = currNode.nodeX + d[0];
                int nextY = currNode.nodeY + d[1];
                if (inArea(nextX, nextY) && !visited[nextX][nextY]) {
                    queue.add(new Node(nextX, nextY, Math.max(dist, Math.abs(heights[nextX][nextY] - heights[currNode.nodeX][currNode.nodeY]))));
                }
            }
        }
        for (int i = 0; i < R * C; i++) {
            System.out.println(ans[i]);
        }
        return ans[R * C - 1];
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public class Node implements Comparable<Node> {
        public int nodeX;
        public int nodeY;
        public int dist;
        public Node(int nodeX, int nodeY, int dist) {
            this.nodeX = nodeX;
            this.nodeY = nodeY;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) {
        int[][] heights = new int[][]{{4,3,4,10,5,5,9,2},{10,8,2,10,9,7,5,6},{5,8,10,10,10,7,4,2},{5,1,3,1,1,3,1,9},{6,4,10,6,10,9,4,6}};
        //int[][] heights = new int[][]{{1,2,2},{3,8,2},{5,3,5}};
        System.out.println((new PathWithMinEffort1631()).minimumEffortPath(heights));
    }
}
