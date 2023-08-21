package com.sky.hiwise.algorithms.leetcode.dynamic.stone;

/**
 * @date: 2023-05-29 19:52
 **/
public class StoneGame1563 {

    /**
     * 1563. 石子游戏 V
     * 几块石子 排成一行 ，每块石子都有一个关联值，关联值为整数，由数组 stoneValue 给出。
     * 游戏中的每一轮：Alice 会将这行石子分成两个 非空行（即，左侧行和右侧行）；Bob 负责计算每一行的值，即此行中所有石子的值的总和。
     * Bob 会丢弃值最大的行，Alice 的得分为剩下那行的值（每轮累加）。如果两行的值相等，Bob 让 Alice 决定丢弃哪一行。下一轮从剩下的那一行开始。
     * 只 剩下一块石子 时，游戏结束。Alice 的分数最初为 0 。
     * 返回 Alice 能够获得的最大分数 。
     * 示例 1：
     * 输入：stoneValue = [6,2,3,4,5,5]
     * 输出：18
     * 解释：在第一轮中，Alice 将行划分为 [6，2，3]，[4，5，5] 。左行的值是 11 ，右行的值是 14 。Bob 丢弃了右行，Alice 的分数现在是 11 。
     * 在第二轮中，Alice 将行分成 [6]，[2，3] 。这一次 Bob 扔掉了左行，Alice 的分数变成了 16（11 + 5）。
     * 最后一轮 Alice 只能将行分成 [2]，[3] 。Bob 扔掉右行，Alice 的分数现在是 18（16 + 2）。游戏结束，因为这行只剩下一块石头了。
     * @param stoneValue
     * @return
     */
    int[][] f;
    int[] sv;
    public int stoneGameV(int[] stoneValue) {
        int len = stoneValue.length;
        f = new int[len][len];
        sv = stoneValue;

        return dfs(0, len - 1);
    }

    public int dfs(int left, int right) {
        if (left == right) {
            return 0;
        }

        if (f[left][right] != 0) {
            return f[left][right];
        }
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += sv[i];
        }

        int sumLeft = 0;
        for (int i = left; i < right; i++) {
            sumLeft += sv[i];
            int sumRight = sum - sumLeft;
            if (sumLeft < sumRight) {
                f[left][right] = Math.max(f[left][right], dfs(left, i) + sumLeft);
            } else if (sumLeft > sumRight) {
                f[left][right] = Math.max(f[left][right], dfs(i + 1, right) + sumRight);
            } else {
                f[left][right] = Math.max(f[left][right], Math.max(dfs(left, i), dfs(i + 1, right)) + sumLeft);
            }
        }
        return f[left][right];
    }
    /**
     * 我们用 f[l][r] 表示当 Alice 面对数组stoneValue 中从位置l 到 r 这一段连续的石子时，她能获得的最大分数。
     */

    public static void main(String[] args) {
        int[] stv = new int[]{5,5};
        System.out.println(new StoneGame1563().stoneGameV(stv));
    }
}
