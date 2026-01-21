package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;

/**
 * @date: 2022-10-20 17:10
 **/
public class Knapsack {

    /**
     * 01 背包问题
     * 有 n 个物品和容量为 C 的背包，第 i 件物品的体积为 c[i]，价值为 v[i]。现在的目标是确定要将哪些物体放入背包，以保证在体积 不超过背包容量 的前提下，背包内的 总价值最高？
     * f[i][v] = max(f[i-1][v], f[i-1][v-c[i]] + w[i])
     */
    public int maxValue(int[] v, int[] c, int capacity) {
        int n = v.length;
        int[][] f = new int[n][capacity + 1];
        //i = 0 初始化 第0个物品放入背包的价值
        for (int j = 0; j <= capacity; j++) {
            f[0][j] = (j >= c[0] ? v[0] : 0);
        }
        for(int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= c[i]) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - c[i]] + v[i]);
                }
            }
        }

        return  f[n - 1][capacity];
    }

    public int[][] memo;
    private int bestValue(int[] v, int[] c, int index, int capacity) {
        if (index < 0 || capacity <= 0) {
            return 0;
        }
        if (memo[index][capacity] != -1) {
            return memo[index][capacity];
        }
        int res = bestValue(v, c, index - 1, capacity);
        if (capacity >= c[index]) {
            res = Math.max(res, bestValue(v, c, index - 1, capacity - c[index]) + v[index]);
        }
        memo[index][capacity] = res;
        return res;
    }

    public int maxValue2(int[] v, int[] c, int capacity) {
        int n = v.length;
        memo = new int[n][capacity + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return bestValue(v, c, n - 1, capacity);
    }

}
