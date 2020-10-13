package com.sky.hiwise.algorithms.leetcode.array;

public class FindFirstAndLastPos34 {

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
     * 找出给定目标值在数组中的开始位置和结束位置。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * 如果数组中不存在目标值，返回 [-1, -1]。
     * 示例 1:
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * 示例 2:
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int[] ans = new int[]{-1, -1};
        boolean findStart = false;
        boolean findEnd = false;
        while(start <= end) {
            if (findStart && findEnd) {
                break;
            }
            if (nums[start] < target) {
                start++;
            } else if (nums[start] == target) {
                ans[0] = start;
                findStart = true;
            }
            if (nums[end] > target) {
                end --;
            } else if (nums[end] == target) {
                ans[1] = end;
                findEnd = true;
            }
        }
        return ans;
    }
}
