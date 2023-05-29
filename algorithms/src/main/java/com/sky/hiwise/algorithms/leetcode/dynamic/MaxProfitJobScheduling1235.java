package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @date: 2022-10-24 16:50
 **/
public class MaxProfitJobScheduling1235 {

    /**
     *1235. 规划兼职工作
     * 你打算利用空闲时间来做兼职工作赚些零花钱。
     * 这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
     * 给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。
     * 注意，时间上出现重叠的 2 份工作不能同时进行。
     * 如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
     * 输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
     * 输出：120
     * 解释：
     * 我们选出第 1 份和第 4 份工作，
     * 时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximum-profit-in-job-scheduling
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[1]));
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int k = searchK(jobs, i - 1, jobs[i - 1][0]);  //查找结束时间小于i-1份工作开始时间的工作数量
            dp[i] = Math.max(dp[i - 1], dp[k] + jobs[i - 1][2]);
        }
        return dp[n];
    }

    public int searchK(int[][] jobs, int right, int target) {
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (jobs[mid][1] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 我们首先将兼职工作按结束时间 \textit{endTime}endTime 从小到大进行排序。使用 \textit{dp}[i]dp[i] 表示前 ii 份兼职工作可以获得的最大报酬，初始时 \textit{dp}[0] = 0dp[0]=0，那么对于 i \gt 0i>0，我们有以下转移方程：
     *
     * \textit{dp}[i] = \max(\textit{dp}[i - 1], \textit{dp}[k] + \textit{profit}[i - 1])
     * dp[i]=max(dp[i−1],dp[k]+profit[i−1])
     *
     * 其中 kk 表示满足结束时间小于等于第 i - 1i−1 份工作开始时间的兼职工作数量，可以通过二分查找获得。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/maximum-profit-in-job-scheduling/solution/gui-hua-jian-zhi-gong-zuo-by-leetcode-so-gu0e/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
