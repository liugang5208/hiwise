package com.sky.hiwise.algorithms.leetcode.math;

public class FindMajorityElementLCCI1710 {
    /**
     * 面试题 17.10. 主要元素
     * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
     * 示例 1：
     * 输入：[1,2,5,9,5,9,5,5,5]
     * 输出：5
     * 示例 2：
     *
     * 输入：[3,2]
     * 输出：-1
     */
    public int majorityElement(int[] nums) {
        int cnt = 0, x = -1;
        for (int i : nums) {
            if (cnt == 0) {
                x = i;
                cnt = 1;
            } else {
                cnt += x == i ? 1 : -1;
            }
        }
        cnt = 0;
        for (int i : nums) {
            if (x == i) {
                cnt++;
            }
        }
        return cnt > nums.length / 2 ? x : -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3};
        System.out.println((new FindMajorityElementLCCI1710()).majorityElement(nums));
    }
}
