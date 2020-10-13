package com.sky.hiwise.algorithms.leetcode.array;

public class SubArrayProductLessThanK713 {
    /**
     * 713. 乘积小于K的子数组
     * 给定一个正整数数组 nums。
     * 找出该数组内乘积小于 k 的连续的子数组的个数。
     * 示例 1:
     * 输入: nums = [10,5,2,6], k = 100
     * 输出: 8
     * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
     * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
     * 说明:
     * 0 < nums.length <= 50000
     * 0 < nums[i] < 1000
     * 0 <= k < 10^6
     * 通过次数9,172提交次数25,084
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int ans = 0, multi = 1, left = 0;
        for (int right = 0; right < nums.length; right++) {
            multi = multi * nums[right];
            while (multi >= k) {
                multi = multi / nums[left++];
            }
            ans += right - left + 1;
        }
        return ans;
    }
    /**
     * 分析
     * 对于每个 \mathrm{right}right，我们需要找到最小的 \mathrm{left}left，满足 \prod_{i=\mathrm{left}}^\mathrm{right} \mathrm{nums}[i] < k∏
     * i=left
     * right
     * ​
     *  nums[i]<k。由于当 \mathrm{left}left 增加时，这个乘积是单调不增的，因此我们可以使用双指针的方法，单调地移动 \mathrm{left}left。
     * 算法
     * 我们使用一重循环枚举 \mathrm{right}right，同时设置 \mathrm{left}left 的初始值为 0。在循环的每一步中，表示 \mathrm{right}right 向右移动了一位，将乘积乘以 \mathrm{nums}[\mathrm{right}]nums[right]。此时我们需要向右移动 \mathrm{left}left，直到满足乘积小于 kk 的条件。在每次移动时，需要将乘积除以 \mathrm{nums}[\mathrm{left}]nums[left]。当 \mathrm{left}left 移动完成后，对于当前的 \mathrm{right}right，就包含了 \mathrm{right} - \mathrm{left} + 1right−left+1 个乘积小于 kk 的连续子数组。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k/solution/cheng-ji-xiao-yu-kde-zi-shu-zu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
