package com.sky.hiwise.algorithms.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class IncreasingSubsequences491 {

    /**
     * 491. 递增子序列
     * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     *
     * 示例:
     *
     * 输入: [4, 6, 7, 7]
     * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
     * 说明:
     *
     * 给定数组的长度不会超过15。
     * 数组中的整数范围是 [-100,100]。
     * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
     */
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> stack = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums, -1, 0);
        return ans;
    }

    public void dfs(int[] nums, int last, int pos) {
        if (nums.length == pos) {
            return;
        }
        //nums[pos] >= stack.get(stack.size() - 1) 递增
        if ((stack.isEmpty() || nums[pos] >= stack.get(stack.size() - 1)) && isFirst(nums, last, pos)) {
            stack.add(nums[pos]);
            if (stack.size() >= 2) {
                ans.add(new ArrayList<>(stack));
            }
            dfs(nums, pos, pos + 1);
            stack.remove(stack.size() - 1);
        }
        dfs(nums, last, pos + 1);
    }

    public boolean isFirst(int[] nums, int last, int pos) {
        for (int i = last + 1; i < pos; i++) {
            if (nums[i] == nums[pos]) {
                return false;
            }
        }
        return true;
    }
}
