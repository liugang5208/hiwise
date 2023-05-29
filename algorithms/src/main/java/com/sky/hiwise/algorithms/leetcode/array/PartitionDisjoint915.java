package com.sky.hiwise.algorithms.leetcode.array;

/**
 * @date: 2022-10-24 16:15
 **/
public class PartitionDisjoint915 {

    /**
     * 给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
     *
     * left 中的每个元素都小于或等于 right 中的每个元素。
     * left 和 right 都是非空的。
     * left 的长度要尽可能小。
     * 在完成这样的分组后返回 left 的 长度 。
     *
     * 用例可以保证存在这样的划分方法。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [5,0,3,8,6]
     * 输出：3
     * 解释：left = [5,0,3]，right = [8,6]
     * 示例 2：
     *
     * 输入：nums = [1,1,1,0,6,12]
     * 输出：4
     * 解释：left = [1,1,1,0]，right = [6,12]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/partition-array-into-disjoint-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int[] minRight = new int[n];
        minRight[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minRight[i] = Math.min(minRight[i + 1], nums[i]);
        }
        int maxLeft = 0;
        for (int i = 0; i < n - 1; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);
            if (maxLeft <= minRight[i + 1]) {
                return i + 1;
            }
        }
        return n - 1;
    }
}
