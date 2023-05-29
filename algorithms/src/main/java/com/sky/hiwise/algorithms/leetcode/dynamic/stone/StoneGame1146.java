package com.sky.hiwise.algorithms.leetcode.dynamic.stone;

/**
 * @date: 2023-05-29 16:05
 **/
public class StoneGame1146 {

    /**
     * 1406. 石子游戏 III
     * Alice 和 Bob 用几堆石子在做游戏。几堆石子排成一行，每堆石子都对应一个得分，由数组 stoneValue 给出。
     * Alice 和 Bob 轮流取石子，Alice 总是先开始。在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子 。比赛一直持续到所有石头都被拿走。
     * 每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 0 。比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。
     * 假设 Alice 和 Bob 都采取 最优策略 。如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，平局（分数相同）返回 "Tie" 。
     * 示例 1：
     * 输入：values = [1,2,3,7]
     * 输出："Bob"
     * 解释：Alice 总是会输，她的最佳选择是拿走前三堆，得分变成 6 。但是 Bob 的得分为 7，Bob 获胜。
     * @param stoneValue
     * @return
     */
    public String stoneGameIII(int[] stoneValue) {
        int len = stoneValue.length;
        int[] dp = new int[50003];
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += stoneValue[i];
            dp[i] = Integer.MIN_VALUE;
            for (int j = 1; j <= 3; j++) {
                dp[i] = Math.max(dp[i], sum - dp[i + j]);
            }
        }
        if (sum - dp[0] == dp[0]) {
            return "Tie";
        } else if (sum - dp[0] > dp[0]) {
            return "Bob";
        }
        return "Alice";
    }
    /**
     * 容易让人魔怔的零和博弈！
     * 为了便于理解，我们假设下标从 1 开始。
     * 设 dp[i] 代表在 [i...n] 上，先手采取最优策略的得分。注意：这里的先手并不是特指Alice或Bob，而是指在 [i...n] 这个局面下先选择的人。
     * 因为必须拿 1 或 2 或 3 堆，所以 dp[n] = stoneValue[n]，即只有一堆时，先手必须拿走，无论该数字的正负。
     * 当 i ∈ [1, n-1] 时，先手有多种策略可选，但先手一定会选择让后手得分最少的策略。因为是零和博弈，总数就那些，对手得分少了，自己得分就高。
     * 根据题意，先手共有三种策略 j = 1 或 j = 2 或 j = 3，对应的，在后手的回合，后手会面临三种局面，即从 [i+1, n]，[i+2, n]，[i+3, n] 选取最优解。
     * 当然，后手虽然无法选择面临的局面，但他可以选择每种局面中的最优策略。
     * 而先手虽然无法改变后手的策略选择，但可以决定后手面临的局面，先手必然让后手面临三种局面中得分最少的局面！！
     *
     * 品，细品，品完这两句再看下面！
     * 在局面 [i,n] 中，先手选择一块时，自己的最高得分为：
     * A = stoneValue[i] + sum(i+1, n) - dp[i+1]
     * 先手选择两块时，自己的最高得分为：
     * B = stoneValue[i, i+1]+ sum(i+2, n) - dp[i+2]
     * 先手选择两块时，自己的最高得分为：
     * C = stoneValue[i, i+1,i+2]+ sum(i+3, n) - dp[i+3]
     * 腹黑如先手，肯定会选择得分最大的策略！
     * 再细品一下状态转移方程：当先手选完 j 堆石头后，游戏进入到下一回合！先手变后手，后手变先手!
     * 此时的先手依然会选择最优策略即 dp[i+j]，对于上一局的先手来说，他只能获的剩下得部分，即 sum(i+j, n) - dp[i+j]。
     */
}
