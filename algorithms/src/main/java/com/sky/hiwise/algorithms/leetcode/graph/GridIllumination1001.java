package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @date: 2022-02-09 17:40
 **/
public class GridIllumination1001 {

    /**
     * 1001. 网格照明
     * 在大小为 n x n 的网格 grid 上，每个单元格都有一盏灯，最初灯都处于 关闭 状态。
     * 给你一个由灯的位置组成的二维数组 lamps ，其中 lamps[i] = [rowi, coli] 表示 打开 位于 grid[rowi][coli] 的灯。即便同一盏灯可能在 lamps 中多次列出，不会影响这盏灯处于 打开 状态。
     * 当一盏灯处于打开状态，它将会照亮 自身所在单元格 以及同一 行 、同一 列 和两条 对角线 上的 所有其他单元格 。
     * 另给你一个二维数组 queries ，其中 queries[j] = [rowj, colj] 。对于第 j 个查询，如果单元格 [rowj, colj] 是被照亮的，则查询结果为 1 ，否则为 0 。在第 j 次查询之后 [按照查询的顺序] ，关闭 位于单元格 grid[rowj][colj] 上及相邻 8 个方向上（与单元格 grid[rowi][coli] 共享角或边）的任何灯。
     * 返回一个整数数组 ans 作为答案， ans[j] 应等于第 j 次查询 queries[j] 的结果，1 表示照亮，0 表示未照亮。
     * 示例 1：
     * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
     * 输出：[1,0]
     * 解释：最初所有灯都是关闭的。在执行查询之前，打开位于 [0, 0] 和 [4, 4] 的灯。第 0 次查询检查 grid[1][1] 是否被照亮（蓝色方框）。该单元格被照亮，所以 ans[0] = 1 。然后，关闭红色方框中的所有灯。
     * 第 1 次查询检查 grid[1][0] 是否被照亮（蓝色方框）。该单元格没有被照亮，所以 ans[1] = 0 。然后，关闭红色矩形中的所有灯。
     */
    private int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1},
            {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    int R, C;
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        R = n; C = n;
        Map<Integer, Integer> col = new HashMap<>();
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> diag = new HashMap<>();
        Map<Integer, Integer> antiDiag = new HashMap<>();
        Set<Long> points = new HashSet<>();
        for (int[] lamp : lamps) {
            if (!points.add(hash(lamp[0], lamp[1]))) {
                continue;
            }
            row.put(lamp[0], row.getOrDefault(lamp[0], 0) + 1);
            col.put(lamp[1], col.getOrDefault(lamp[1], 0) + 1);
            diag.put(lamp[0] - lamp[1], diag.getOrDefault(lamp[0] - lamp[1], 0) + 1);
            antiDiag.put(lamp[0] + lamp[1], antiDiag.getOrDefault(lamp[0] + lamp[1], 0) + 1);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int r = queries[i][0], c = queries[i][1];
            if (row.getOrDefault(r, 0) > 0 || col.getOrDefault(c, 0) > 0 || diag.getOrDefault(r - c, 0) > 0 || antiDiag.getOrDefault(r + c, 0) > 0) {
                ans[i] = 1;
            }
            for (int d = 0; d < 8; d++) {
                int nextR = r + dirs[d][0];
                int nextC = c + dirs[d][1];
                if (inArea(nextR, nextC) && points.remove(hash(nextR, nextC))) {
                    row.put(nextR, row.get(nextR) - 1);
                    if (row.get(nextR) == 0) {
                        row.remove(nextR);
                    }
                    col.put(nextC, col.get(nextC) - 1);
                    if (col.get(nextC) == 0) {
                        col.remove(nextC);
                    }
                    diag.put(nextR - nextC, diag.get(nextR - nextC) - 1);
                    if (diag.get(nextR - nextC) == 0) {
                        diag.remove(nextR - nextC);
                    }
                    antiDiag.put(nextC + nextR, antiDiag.get(nextC + nextR) - 1);
                    if (antiDiag.get(nextC + nextR) == 0) {
                        antiDiag.remove(nextC + nextR);
                    }
                }
            }
        }
        return ans;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public long hash(int x, int y) {
        return (long) x + ((long) y << 32);
    }
}
