package com.sky.hiwise.algorithms.leetcode.dynamic.stone;

/**
 * @date: 2023-05-29 16:20
 **/
public class StoneGame1150 {

    /**
     * 1510. 石子游戏 IV
     * Alice 和 Bob 两个人轮流玩一个游戏，Alice 先手。
     * 一开始，有 n 个石子堆在一起。每个人轮流操作，正在操作的玩家可以从石子堆里拿走 任意 非零 平方数 个石子。
     * 如果石子堆里没有石子了，则无法操作的玩家输掉游戏。
     * 给你正整数 n ，且已知两个人都采取最优策略。如果 Alice 会赢得比赛，那么返回 True ，否则返回 False 。
     * 示例 1：
     * 输入：n = 1
     * 输出：true
     * 解释：Alice 拿走 1 个石子并赢得胜利，因为 Bob 无法进行任何操作。
     * 示例 2：
     * 输入：n = 2
     * 输出：false
     * 解释：Alice 只能拿走 1 个石子，然后 Bob 拿走最后一个石子并赢得胜利（2 -> 1 -> 0）。
     * @param n
     * @return
     */
    public boolean winnerSquareGame(int n) {
        boolean[] f = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k * k <= i; k++) {
                if (!f[i - k * k]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
    /**
     * 方法一：动态规划
     * 我们用f[i] 表示先手在面对i 颗石子时是否处于必胜态（会赢得比赛）。由于先手和后手都采取最优策略，那么
     * f[i] 为必胜态，当且仅当存在某个
     * f[i−k^2] 为必败态。也就是说，当先手在面对i 颗石子时，可以选择取走k^2颗，剩余的i−k^2
     * 颗对于后手来说是必败态，因此先手会获胜。
     * 我们可以写出状态转移方程：
     * f[i] = true any f[i - k * k] == false && 1 <= k * k <= i
     * f[i] = false 其他情况
     * f[0] = false 即没有石子时，先手会输掉游戏  最终的答案即为f[n]
     */
}
