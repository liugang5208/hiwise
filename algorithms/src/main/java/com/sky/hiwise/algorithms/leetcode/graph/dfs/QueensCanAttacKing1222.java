package com.sky.hiwise.algorithms.leetcode.graph.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueensCanAttacKing1222 {
    /**
     * 1222. 可以攻击国王的皇后
     * 在一个 8x8 的棋盘上，放置着若干「黑皇后」和一个「白国王」。
     * 「黑皇后」在棋盘上的位置分布用整数坐标数组 queens 表示，「白国王」的坐标用数组 king 表示。
     * 「黑皇后」的行棋规定是：横、直、斜都可以走，步数不受限制，但是，不能越子行棋。
     * 请你返回可以直接攻击到「白国王」的所有「黑皇后」的坐标（任意顺序）。
     * 示例 1：
     * 输入：queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
     * 输出：[[0,1],[1,0],[3,3]]
     * 解释：
     * [0,1] 的皇后可以攻击到国王，因为他们在同一行上。
     * [1,0] 的皇后可以攻击到国王，因为他们在同一列上。
     * [3,3] 的皇后可以攻击到国王，因为他们在同一条对角线上。
     * [0,4] 的皇后无法攻击到国王，因为她被位于 [0,1] 的皇后挡住了。
     * [4,0] 的皇后无法攻击到国王，因为她被位于 [1,0] 的皇后挡住了。
     * [2,4] 的皇后无法攻击到国王，因为她和国王不在同一行/列/对角线上。
     */
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
        boolean[][] flags = new boolean[8][8];
        for (int[] queen : queens) {
            flags[queen[0]][queen[1]] = true;
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int d = 0; d < dirs.length; d++) {
            for (int x = king[0], y = king[1]; inArea(x, y); x += dirs[d][0], y += dirs[d][1]) {
                if (flags[x][y]) {
                    ans.add(Arrays.asList(x, y));
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * 思路就是以国王为起点往八个方向迭代，循环结束条件为出界((x < 0 || x >= 8) || (y < 0 || y >= 8))或者在这个方向上找到第一个皇后，
     * 那么结束当前这个循环，继续迭代下一个方向。
     * 作者：LSZ
     * 链接：https://leetcode-cn.com/problems/queens-that-can-attack-the-king/solution/javadie-dai-shi-xian-ba-fang-xiang-cha-zhao-by-lsz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param x
     * @param y
     * @return
     */

    private boolean inArea(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public static void main(String[] args) {
        int[][] queens = new int[][]{{0,1},{1,0},{4,0},{0,4},{3,3},{2,4}};
        int[] king = new int[]{0,0};
        System.out.println((new QueensCanAttacKing1222()).queensAttacktheKing(queens, king));
    }
}
