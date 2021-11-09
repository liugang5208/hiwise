package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.HashMap;
import java.util.Map;

public class LongestSubSeqDiff1218 {
    /**
     * 1218. 最长定差子序列
     * 给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
     * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
     * 示例 1：
     * 输入：arr = [1,2,3,4], difference = 1
     * 输出：4
     * 解释：最长的等差子序列是 [1,2,3,4]。
     * 示例 2：
     * 输入：arr = [1,3,5,7], difference = 1
     * 输出：1
     * 解释：最长的等差子序列是任意单个元素。
     * 示例 3：
     * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
     * 输出：4
     * 解释：最长的等差子序列是 [7,5,3,1]。
     */
    public int longestSubsequence(int[] arr, int difference) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a - difference, 0) + 1);
            ans = Math.max(ans, map.get(a));
        }
        return ans;
    }
    /**
     * 方法一：动态规划
     * 下文为方便叙述将difference 简写成 d。
     * 我们从左往右遍历 arr，并计算出以 arr[i] 为结尾的最长的等差子序列的长度，取所有长度的最大值，即为答案。
     * 令 dp[i] 表示以 arr[i] 为结尾的最长的等差子序列的长度，我们可以在arr[i] 左侧找到满足 arr[j]=arr[i]−d 的元素，
     * 将 arr[i] 加到以 arr[j] 为结尾的最长的等差子序列的末尾，这样可以递推地从 dp[j] 计算出 dp[i]。
     * 由于我们是从左往右遍历 arr 的，对于两个相同的元素，下标较大的元素对应的 dp 值不会小于下标较小的元素对应的dp 值，
     * 因此下标 j 可以取满足 j<i 且 arr[j]=arr[i]−d 的所有下标的最大值。故有转移方程dp[i]=dp[j]+1
     * 由于我们总是在左侧找一个最近的等于 arr[i]−d 元素并取其对应 dp 值，因此我们直接用 dp[v] 表示以 v 为结尾的最长的等差子序列的长度，
     * 这样 dp[v−d] 就是我们要找的左侧元素对应的最长的等差子序列的长度，因此转移方程可以改为dp[v]=dp[v−d]+1
     * 最后答案为 max{dp}。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference/solution/zui-chang-ding-chai-zi-xu-lie-by-leetcod-xkua/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
