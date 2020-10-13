package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;
import java.util.HashMap;

public class LongestFibonacciSubsequence873 {

    /**
     * 873. 最长的斐波那契子序列的长度
     * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
     * n >= 3
     * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
     * 给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
     * （回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
     * 示例 1：
     * 输入: [1,2,3,4,5,6,7,8]
     * 输出: 5
     * 解释:
     * 最长的斐波那契式子序列为：[1,2,3,5,8] 。
     * 示例 2：
     * 输入: [1,3,7,11,12,14,18]
     * 输出: 3
     * 解释:
     * 最长的斐波那契式子序列有：
     * [1,11,12]，[3,11,14] 以及 [7,11,18] 。
     * 提示：
     * 3 <= A.length <= 1000
     * 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
     * （对于以 Java，C，C++，以及 C# 的提交，时间限制被减少了 50%）
     * @param A
     * @return
     */
    public int lenLongestFibSubseq(int[] A) {
        int len = A.length;
        HashMap<Integer, Integer> index = new HashMap<>();
        for(int i = 0; i < len; i++) {
            index.put(A[i], i);
        }
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++) {
            Arrays.fill(dp[i], 2);
        }

        int ans = 0;
        for(int k = 0; k < len; k++ ) {
            for(int j = 0; j < k; j++) {
                int i = index.getOrDefault(A[k] - A[j], -1);
                if (i >= 0 && A[i] < A[j]) {
                    dp[j][k] = dp[i][j] + 1;
                    ans = Math.max(ans, dp[j][k]);
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }

    /**
     * 1、重新定义状态dp[i][j]:代表以A[i]和A[j]构成的最长斐波那契子序列的长度，其中A[j]表示当前的序列末端
     * 2、状态转移方程：dp[j][k]=max{dp[i][j]}+1:表示：当新一个元素加入时，以A[k]结尾，用A[j],A[k]所构成的最长
     * 斐波那契子序列的长度就等于以A[i],A[j]构成的子序列的长度加一，
     * 3、条件：i<j<k,且A[k]=A[i]+A[j],所以A[i]=A[K]-A[j],只需要判断A[i]在0 ~ j-1这个序列中就可以了
     * @param args
     */

    public static void main(String[] args) {
        int[] test = new int[]{1,2,3,4,5,6,7,8};
        System.out.println((new LongestFibonacciSubsequence873()).lenLongestFibSubseq(test));
    }
}
