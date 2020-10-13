package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.*;

public class WaterProblem365 {
    /**
     * 365. 水壶问题
     * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。
     * 请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
     * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
     * 你允许：
     * 装满任意一个水壶
     * 清空任意一个水壶
     * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
     * 示例 1: (From the famous "Die Hard" example)
     * 输入: x = 3, y = 5, z = 4
     * 输出: True
     * 示例 2:
     * 输入: x = 2, y = 6, z = 5
     * 输出: False
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static boolean canMeasureWater(int x, int y, int z) {
        if (x == z || y == z || (x + y) == z) {
            return true;
        }
        if (x + y < z) {
            return false;
        }
        HashSet<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited.add("0,0");
        while(!queue.isEmpty()) {
            int[] curr = queue.remove();
            int currX = curr[0], currY = curr[1];
            List<int[]> nexts = new ArrayList<>();
            nexts.add(new int[]{x, currY});
            nexts.add(new int[]{0, currY});
            nexts.add(new int[]{currX, y});
            nexts.add(new int[]{0, y});
            int xtoy = Math.min(currX, y - currY);
            int ytox = Math.min(currY, x - currX);
            nexts.add(new int[]{currX - xtoy, currY + xtoy});
            nexts.add(new int[]{currX + ytox, currY - ytox});
            for (int[] next : nexts) {
                if (!visited.contains(next[0] + "," + next[1])) {
                    visited.add(next[0] + "," + next[1]);
                    queue.add(next);
                    if (next[0] == z || next[1] == z || (next[0] + next[1]) == z) {
                        return true;
                    }
                }
            }
        }
        return  false;
    }

    public static void main(String[] args) {
        System.out.println(canMeasureWater(2,6,5));

    }
}
