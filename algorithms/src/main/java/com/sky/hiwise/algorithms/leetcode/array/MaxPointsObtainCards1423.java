package com.sky.hiwise.algorithms.leetcode.array;

public class MaxPointsObtainCards1423 {
    /**
     * 1423. 可获得的最大点数
     * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
     * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
     * 你的点数就是你拿到手中的所有卡牌的点数之和。
     * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
     * 示例 1：
     * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
     * 输出：12
     * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
     */
    public int maxScore(int[] cardPoints, int k) {
        if (k == cardPoints.length) {
            int ans = 0;
            for (int c : cardPoints) {
                ans += c;
            }
            return ans;
        }
        return dfs(cardPoints, 0, cardPoints.length - 1, k);
    }

    public int dfs(int[] points, int left, int right, int k) {
        if (k == 1) {
            return Math.max(points[left], points[right]);
        }
        int leftMax = dfs(points, left + 1, right, k - 1) + points[left];
        int rightMax = dfs(points, left, right - 1, k - 1) + points[right];
        return Math.max(leftMax, rightMax);
    }

    public int maxScore1(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int sum = 0;
        for (int c : cardPoints) {
            sum += c;
        }
        if (k == len) {
            return sum;
        }

        int currSum = 0;
        for (int i = 0; i < len - k; i++) {
            currSum += cardPoints[i];
        }
        /**
         * 逆向思维，按题中意思取左取右得最大值，反过来就是求中间连续子数组得和最小
         * 由于要取 k 张牌，所以反过来就是在中间找连续的长度为 len(carPoints)-k 的子数组使其和最小，这以部分就是滑动窗口
         * 变量存储滑窗的最小值，这里先按初始位置赋初值
         */
        int ans = currSum;
        int left = 0, right = len - k - 1;
        for (int i = 0; i < k; i++) {
            currSum = currSum + cardPoints[++right] - cardPoints[left++];
            ans = Math.min(ans, currSum);
        }
        return sum - ans;
    }
}
