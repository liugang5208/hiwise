package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.Arrays;

public class MinIncrementMakeArrayUnique945 {
    /**
     * 945. 使数组唯一的最小增量
     * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
     * 返回使 A 中的每个值都是唯一的最少操作次数。
     * 示例 1:
     * 输入：[1,2,2]
     * 输出：1
     * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
     * 示例 2:
     * 输入：[3,2,1,2,1,7]
     * 输出：6
     * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
     * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
     */
    public int minIncrementForUnique(int[] A) {
        //先排序
        Arrays.sort(A);
        int move = 0;
        for (int i = 1; i < A.length; i++) {
            // 遍历数组，若当前元素小于等于它的前一个元素，则将其变为前一个数+1
            if (A[i] <= A[i - 1]) {
                int pre = A[i];
                A[i] = A[i - 1] + 1;
                move += A[i] - pre;
            }
        }
        return move;

    }

    //通过+1方式，使后面比前面大 需要多少步
    public static int increment(int[] nums) {
        int max = Integer.MIN_VALUE;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (i != 0 && nums[i] < nums[i - 1]) {
                ans = Math.max(ans, max - nums[i] + 1);
                max = max + 1;
                nums[i] = max;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,1,1};
        System.out.println(increment(nums));
    }

}
