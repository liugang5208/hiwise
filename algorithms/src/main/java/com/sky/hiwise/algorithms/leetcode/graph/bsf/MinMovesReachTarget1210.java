package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.LinkedList;
import java.util.Queue;

public class MinMovesReachTarget1210 {
    /**
     * 1210. 穿过迷宫的最少移动次数
     * 你还记得那条风靡全球的贪吃蛇吗？
     *
     * 我们在一个 n*n 的网格上构建了新的迷宫地图，蛇的长度为 2，也就是说它会占去两个单元格。蛇会从左上角（(0, 0) 和 (0, 1)）开始移动。
     * 我们用 0 表示空单元格，用 1 表示障碍物。蛇需要移动到迷宫的右下角（(n-1, n-2) 和 (n-1, n-1)）。
     * 每次移动，蛇可以这样走：
     * 如果没有障碍，则向右移动一个单元格。并仍然保持身体的水平／竖直状态。
     * 如果没有障碍，则向下移动一个单元格。并仍然保持身体的水平／竖直状态。
     * 如果它处于水平状态并且其下面的两个单元都是空的，就顺时针旋转 90 度。蛇从（(r, c)、(r, c+1)）移动到 （(r, c)、(r+1, c)）。
     * 如果它处于竖直状态并且其右面的两个单元都是空的，就逆时针旋转 90 度。蛇从（(r, c)、(r+1, c)）移动到（(r, c)、(r, c+1)）。
     * 返回蛇抵达目的地所需的最少移动次数。
     *
     * 如果无法到达目的地，请返回 -1。
     */
    public static final Integer RIGHT = 0;
    public static final Integer DOWN = 1;
    public int minimumMoves(int[][] grid) {

        int R = grid.length;
        int C = grid[0].length;
        Queue<Node> queue = new LinkedList<>();
        //x * c + y
        queue.add(new Node(1, 0));
        int step = 0;
        boolean[] visited = new boolean[R * C];
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                visited[node.val] = true;
                int x = node.val / C;
                int y = node.val % C;
                System.out.println("(" + x + "," + y +")");
                int d = node.d;
                if (x == R - 1 && y == C - 1 && d == RIGHT) {
                    return step;
                }
                if (d == RIGHT) {
                    if (y < C - 1 && grid[x][y+1] == 0) {
                        int val = x * C + (y + 1);
                        if (!visited[val]) {
                            queue.add(new Node(val, 0));
                        }
                    }
                    if (x < R - 1 && grid[x + 1][y - 1] == 0 &&  grid[x + 1][y] == 0) {
                        //f(x+1,y,0) || f(x+1,y-1,1)
                        int val = (x + 1) * C + y;
                        if (!visited[val]) {
                            queue.add(new Node(val, 0));
                        }
                        val = (x + 1) * C + (y - 1);
                        if (!visited[val]) {
                            queue.add(new Node(val, 1));
                        }
                    }
                } else if (d == DOWN) {
                    if (x < R - 1 && grid[x + 1][y] == 0) {
                        int val = (x + 1) * C + y;
                        if (!visited[val]) {
                            queue.add(new Node(val, 1));
                        }
                    }
                    //grid[x][y+1]==0 && grid[x-1][y+1]==0 => f(x,y+1,1) || f(x-1, y+1, 0)
                    if (y < C - 1 && grid[x][y+1]==0 && grid[x-1][y+1]==0) {
                        int val = x * C + y + 1;
                        if (!visited[val]) {
                            queue.add(new Node(val, 1));
                        }
                        val = (x - 1) * C + (y + 1);
                        if (!visited[val]) {
                            queue.add(new Node(val, 0));
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,0,0,0,0,1},{1,1,0,0,1,0},{0,0,0,0,1,1},{0,0,1,0,1,0},{0,1,1,0,0,0},{0,1,1,0,0,0}};
        System.out.println((new MinMovesReachTarget1210()).minimumMoves(grid));
    }
    class Node {
        public int val;
        public int d;
        public Node(int val, int d) {
            this.val = val;
            this.d = d;
        }
    }
    /**
     *题目看起来很复杂，但是仔细阅读其实本题非常简单，用DFS或者BFS均可解答,坑爹的是贪吃蛇可以平移。
     * 主要是我们用(x,y,d),来记录当前的状态，(x,y)代表当前贪吃蛇头部所在的位置，d代表当前所处的方向,水平或者垂直。我们用d = 0代表当前处在水平方向，d = 1代表当前处在垂直方向。
     * 我们设置状态dp[x][y][i],代表贪吃蛇处在(x,y)位置处，且处于i方向。
     * 后续的状态和位置分为三类，状态转移方程如下:
     * f(x,y,0) -> grid[x][y+1] == 0 =>f(x,y+1,0)
     * f(x,y,0) -> grid[x+1][y-1] == 0 && grid[x+1][y]==0 => f(x+1,y,0) || f(x+1,y-1,1)
     * f(x,y,1) -> grid[x+1][y] ==0 =>f(x+1,y,1)
     * f(x,y,1) -> grid[x][y+1]==0 && grid[x-1][y+1]==0 => f(x,y+1,1) || f(x-1, y+1, 0)
     * 作者：mike-meng
     * 链接：https://leetcode-cn.com/problems/minimum-moves-to-reach-target-with-rotations/solution/bfs-by-mike-meng-9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
