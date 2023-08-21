package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @date: 2023-06-05 16:24
 **/
public class NumberDistinctAverages2465 {

    /**
     * 2465. 不同的平均值数目
     * 给你一个下标从 0 开始长度为 偶数 的整数数组 nums 。
     * 只要 nums 不是 空数组，你就重复执行以下步骤：
     * 找到 nums 中的最小值，并删除它。
     * 找到 nums 中的最大值，并删除它。
     * 计算删除两数的平均值。
     * 两数 a 和 b 的 平均值 为 (a + b) / 2 。
     * 比方说，2 和 3 的平均值是 (2 + 3) / 2 = 2.5 。
     * 返回上述过程能得到的 不同 平均值的数目。
     * 注意 ，如果最小值或者最大值有重复元素，可以删除任意一个。
     * 示例 1：
     * 输入：nums = [4,1,4,0,3,5]
     * 输出：2
     * 解释：
     * 1. 删除 0 和 5 ，平均值是 (0 + 5) / 2 = 2.5 ，现在 nums = [4,1,4,3] 。
     * 2. 删除 1 和 4 ，平均值是 (1 + 4) / 2 = 2.5 ，现在 nums = [4,3] 。
     * 3. 删除 3 和 4 ，平均值是 (3 + 4) / 2 = 3.5 。
     * 2.5 ，2.5 和 3.5 之中总共有 2 个不同的数，我们返回 2 。
     */
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        Set<Integer> seen = new HashSet<>();
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            seen.add(nums[i] + nums[j]);
        }
        return seen.size();
    }
    /**
     * 方法一：排序 + 哈希表
     * 思路与算法
     * 我们对数组
     * nums 进行排序，随后使用两个指针，初始分别指向
     * nums 首元素和尾元素对数组进行遍历，就可以不断得到当前数组的最大值和最小值。
     * 由于「不同平均值的数目」和「不同和的数目」是等价的，因此在计算时，可以直接求出两个指针指向元素的和，代替平均值，避免浮点运算。我们只需要使用一个哈希集合，将所有的和添加进去，随后哈希集合中的元素个数即为答案。
     */
}
