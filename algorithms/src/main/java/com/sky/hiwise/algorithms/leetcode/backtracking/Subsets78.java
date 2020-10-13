package com.sky.hiwise.algorithms.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets78 {

    /**
     * 78 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     * 示例:
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {

        if (nums.length == 0) {
            return res;
        }
        List<Integer> temp = new ArrayList<>();
        backtrack(0, nums, temp);
        return res;
    }

    private void backtrack(int start, int[] nums, List<Integer> temp) {
        res.add(new ArrayList<>(temp));
        for(int i = start; i < nums.length; i ++) {
            temp.add(nums[i]);
            backtrack(i + 1, nums, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        //System.out.println((new Subsets78()).subsets(nums));
        int[] nums2 = new int[]{1,2,2};
        System.out.println((new Subsets78()).subsetsWithDup(nums2));
    }

    /**
     * 90 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     * 示例:
     * 输入: [1,2,2]
     * 输出:
     * [
     *   [2],
     *   [1],
     *   [1,2,2],
     *   [2,2],
     *   [1,2],
     *   []
     * ]
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) {
            return res;
        }
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        backtrackWithDup(0, nums, temp);
        return res;
    }

    private void backtrackWithDup(int start, int[] nums, List<Integer> temp) {
        res.add(new ArrayList<>(temp));
        for(int i = start; i < nums.length; i ++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            backtrackWithDup(i + 1, nums, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
