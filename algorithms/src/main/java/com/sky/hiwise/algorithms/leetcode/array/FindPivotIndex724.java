package com.sky.hiwise.algorithms.leetcode.array;

import java.util.Arrays;

public class FindPivotIndex724 {
    /**
     * 724. 寻找数组的中心索引
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
     * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     * 示例 1：
     * 输入：
     * nums = [1, 7, 3, 6, 5, 6]
     * 输出：3
     * 解释：
     * 索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
     * 同时, 3 也是第一个符合要求的中心索引。
     * 示例 2：
     * 输入：
     * nums = [1, 2, 3]
     * 输出：-1
     * 解释：
     * 数组中不存在满足此条件的中心索引。
     */
    public int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
    /**
     * 方法一：前缀和
     * 思路
     * 记数组的全部元素之和为total，当遍历到第 i 个元素时，设其左侧元素之和为sum，则其右侧元素之和为total−nums−sum。
     * 左右侧元素相等即为 sum=total−nums−sum，即2×sum+nums=total。
     * 当中心索引左侧或右侧没有元素时，即为零个项相加，这在数学上称作「空和」。在程序设计中我们约定「空和是零」。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-pivot-index/solution/xun-zhao-shu-zu-de-zhong-xin-suo-yin-by-gzjle/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
