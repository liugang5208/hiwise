package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathObstaclesGrid1293 {

    /**
     * 1293. 网格中的最短路径
     * 给你一个 m * n 的网格，其中每个单元格不是 0（空）就是 1（障碍物）。每一步，您都可以在空白单元格中上、下、左、右移动。
     * 如果您 最多 可以消除 k 个障碍物，请找出从左上角 (0, 0) 到右下角 (m-1, n-1) 的最短路径，并返回通过该路径所需的步数。如果找不到这样的路径，则返回 -1。
     * 示例 1：
     * 输入：
     * grid =
     * [[0,0,0],
     *  [1,1,0],
     *  [0,0,0],
     *  [0,1,1],
     *  [0,0,0]],
     * k = 1
     * 输出：6
     * 解释：
     * 不消除任何障碍的最短路径是 10。
     * 消除位置 (3,2) 处的障碍后，最短路径是 6 。该路径是 (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
     * @param grid
     * @param k
     * @return
     */
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int R, C;
    public int shortestPath(int[][] grid, int k) {
        R = grid.length;
        C = grid[0].length;
        if (R == 1 && C == 1) {
            return 0;
        }
        int[][] visited = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(visited[i], -1);
        }
        Queue<Node> queue = new LinkedList<>();
        int minSteps = 0;
        queue.add(new Node(0, 0, 0));
        visited[0][0] = k;
        while (!queue.isEmpty()) {
            minSteps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node currNode = queue.poll();
                int currX = currNode.x, currY = currNode.y, countOne = currNode.countOne;
                for (int d = 0; d < dirs.length; d++) {
                    int nextX = currX + dirs[d][0], nextY = currY + dirs[d][1];
                    if (!inArea(nextX, nextY)) {
                        continue;
                    }
                    if (nextX == R - 1 && nextY == C - 1) {
                        return minSteps;
                    }
                    if (grid[nextX][nextY] == 1 && countOne >= k) {
                        continue;
                    }
                    // 剪枝 - 节点已被访问过，且当前visited记录的剩余障碍物消除次数 >= 当前搜索节点层级的剩余消除次数
                    int countOneNew = countOne + (grid[nextX][nextY] == 1 ? 1 : 0);
                    if (visited[nextX][nextY] != -1 && visited[nextX][nextY] >= k - countOneNew) {
                        continue;
                    }
                    queue.add(new Node(nextX, nextY, countOneNew));
                    visited[nextX][nextY] = k - countOneNew;
                }

            }
        }
        return -1;
    }

    /**
     * 题目类型扩展：
     * 若题目要求求解最小层级搜索（节点间距离固定为1），通过统计层级计数，遇到终止条件终止即可。
     * 若节点间有加权值，求解最短路径时可以在Node中增加cost记录，比较获取最佳值
     * 若需要求解最短路径，可以逆向根据visited访问记录情况回溯
     * 方法一：visited访问标记数组二维 + 贪心 （推荐）
     * 本题精髓在于对标记访问数组 visited值的扩展，常规0|1标记是否访问，但还需要记录走到当前位置所剩的消除障碍物机会，越多越好。因为后面的路障谁都不清楚够不够用。
     *
     *  // BFS对于当前点的下一个点选择，如果grid[i][j]=0则有效入队列 visited[i][j]记录消除障碍次数
     * // 若grid[i][j]=1则看是否还有消除障碍机会，若没有 此点丢弃
     *  // 若有消除障碍机会， （上一个点剩余消除障碍机会 - 1）比visited[i][j] 值比大 此点入队， 小则丢弃（贪心）
     *  // 例子：k=1, 坐标(0,2)可以为消除(0,1)障碍过来的 visited[0][2] = 0，搜索层级为2
     *  // 也可能为不消除任何障碍过来的 visited[0][2] = 1，层级为6，更新visited[0][2] = 1并入队
     *  // 因为到后面还需要消除障碍才能到达目标，先消除障碍走到visited[0][2] = 0的肯定到不了目标...
     *
     * 作者：jin-129
     * 链接：https://leetcode-cn.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/solution/wang-ge-zhong-de-zui-duan-lu-jing-bfssuan-fa-shi-x/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 作者：jin-129
     * 链接：https://leetcode-cn.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/solution/wang-ge-zhong-de-zui-duan-lu-jing-bfssuan-fa-shi-x/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param args
     */

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
        int k = 1;
        System.out.println((new ShortestPathObstaclesGrid1293()).shortestPath(grid, k));

    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    class Node {
        public int x;
        public int y;
        public int countOne;

        public Node(int x, int y, int countOne) {
            this.x = x;
            this.y = y;
            this.countOne = countOne;
        }
    }
}
