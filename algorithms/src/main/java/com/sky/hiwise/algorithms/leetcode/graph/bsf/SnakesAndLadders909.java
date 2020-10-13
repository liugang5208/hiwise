package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SnakesAndLadders909 {

    /**
     * 909. 蛇梯棋
     * N x N 的棋盘 board 上，按从 1 到 N*N 的数字给方格编号，编号 从左下角开始，每一行交替方向。
     * 例如，一块 6 x 6 大小的棋盘，编号如下：
     * r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board[r][c] != -1，那个蛇或梯子的目的地将会是 board[r][c]。
     * 玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
     * 每一回合，玩家需要从当前方格 x 开始出发，按下述要求前进：
     * 选定目标方格：选择从编号 x+1，x+2，x+3，x+4，x+5，或者 x+6 的方格中选出一个目标方格 s ，目标方格的编号 <= N*N。
     * 该选择模拟了掷骰子的情景，无论棋盘大小如何，你的目的地范围也只能处于区间 [x+1, x+6] 之间。
     * 传送玩家：如果目标方格 S 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 S。
     * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，你也不会继续移动。
     * 返回达到方格 N*N 所需的最少移动次数，如果不可能，则返回 -1。
     * 示例：
     * 输入：[
     * [-1,-1,-1,-1,-1,-1],
     * [-1,-1,-1,-1,-1,-1],
     * [-1,-1,-1,-1,-1,-1],
     * [-1,35,-1,-1,13,-1],
     * [-1,-1,-1,-1,-1,-1],
     * [-1,15,-1,-1,-1,-1]]
     * 输出：4
     * 解释：
     * 首先，从方格 1 [第 5 行，第 0 列] 开始。
     * 你决定移动到方格 2，并必须爬过梯子移动到到方格 15。
     * 然后你决定移动到方格 17 [第 3 行，第 5 列]，必须爬过蛇到方格 13。
     * 然后你决定移动到方格 14，且必须通过梯子移动到方格 35。
     * 然后你决定移动到方格 36, 游戏结束。
     * 可以证明你需要至少 4 次移动才能到达第 N*N 个方格，所以答案是 4。
     *
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        int N = board.length;
        Map<Integer, Integer> dist = new HashMap<>();
        dist.put(1, 0);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            int s = queue.poll();
            if (s == N * N) {
                return dist.get(s);
            }
            for (int s2 = s + 1; s2 <= Math.min(s + 6, N * N); s2++) {
                int rc = getRC(s2, N);
                int r = rc / N, c = rc % N;
                int s2Final = board[r][c] == -1 ? s2 : board[r][c];
                if (!dist.containsKey(s2Final)) {
                    dist.put(s2Final, dist.get(s) + 1);
                    queue.add(s2Final);
                }
            }
        }
        return -1;
    }

    public int getRC(int s, int N) {
        int quot = (s - 1) / N;
        int rem = (s - 1) % N;
        //每一行交替方向 需要计算奇偶和反向位置
        int row = N - 1 - quot;
        int col = row % 2 != N % 2 ? rem : N - 1 - rem;
        return row * N + col;
    }
    /**
     * 方法 1：广度优先搜索
     * 想法
     * 由于我们需要找到一条最短路径，广度优先搜索是一种理想的方法。难点在于如何对每个方格枚举所有可能的移动。
     * 算法
     * 假设我们在方格 s 上，我们想知道一次移动之后所有可能的终点 s2。
     * 这需要知道方格 s2 的坐标 get(s2)，这有一个小技巧：我们知道行号每 N 个方格改变一次，所以只依赖于 quot = (s2-1) / N；同样列号依赖于 rem = (s2-1) % N。
     * 由此，我们可以实现一个根据方格 s 的信息进行广度优先搜索。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/snakes-and-ladders/solution/she-ti-qi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
