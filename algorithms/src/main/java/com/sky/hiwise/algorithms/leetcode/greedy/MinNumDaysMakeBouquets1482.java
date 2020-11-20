package com.sky.hiwise.algorithms.leetcode.greedy;

public class MinNumDaysMakeBouquets1482 {
    /**
     * 1482. 制作 m 束花所需的最少天数
     * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
     * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
     * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
     * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
     * 示例 1：
     * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
     * 输出：3
     * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
     * 现在需要制作 3 束花，每束只需要 1 朵。
     * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
     * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
     * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
     * 提示：
     * bloomDay.length == n
     * 1 <= n <= 10^5
     * 1 <= bloomDay[i] <= 10^9
     * 1 <= m <= 10^6
     * 1 <= k <= n
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int left = 0, right = 1000000000;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(bloomDay, m ,k, mid)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public boolean check(int[] bloomDay, int m, int k, int mid) {
        int cnt = 0;
        int len = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= mid) {
                len++;
                if (len == k) {
                    cnt++;
                    len -= k;
                }
            } else {
                len = 0;
            }
        }
        return cnt >= m;
    }

    public static void main(String[] args) {
        int[] bloomDay = new int[]{7,7,7,7,12,7,7};
        int m = 2;
        int k = 3;
        System.out.println((new MinNumDaysMakeBouquets1482()).minDays(bloomDay, m , k));
    }



}
