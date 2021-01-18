package com.sky.hiwise.algorithms.leetcode.math;

public class CheckStraightLine1232 {
    /**
     * 1232. 缀点成线
     * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
     * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
     * 示例 1：
     * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
     * 输出：true
     */
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates == null || coordinates.length == 0) {
            return false;
        }
        int len = coordinates.length;
        int detX = coordinates[0][0], detY = coordinates[0][1];
        for (int i = 0; i < len; i++) {
            coordinates[i][0] -= detX;
            coordinates[i][1] -= detY;
        }

        int A = coordinates[1][1], B = -coordinates[1][0];

        for (int i = 2; i < len; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if (A * x + B * y != 0) {
                return false;
            }
        }
        return true;
    }
}
