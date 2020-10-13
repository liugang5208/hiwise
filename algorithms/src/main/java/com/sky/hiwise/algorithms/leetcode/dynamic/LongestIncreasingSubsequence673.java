package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;

public class LongestIncreasingSubsequence673 {

    /**
     * 673. 最长递增子序列的个数
     * 给定一个未排序的整数数组，找到最长递增子序列的个数。
     * 示例 1:
     * 输入: [1,3,5,4,7]
     * 输出: 2
     * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
     * 示例 2:
     * 输入: [2,2,2,2,2]
     * 输出: 5
     * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
     * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        int[] lengths = new int[len];
        int[] counts = new int[len];
        Arrays.fill(counts, 1);
        for (int j = 0; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (nums[j] > nums[i]) {
                    if (lengths[i] >= lengths[j]) {
                        lengths[j] = lengths[i] + 1;
                        counts[j] = counts[i];
                    } else if (lengths[j] == lengths[i] + 1) {
                        counts[j] += counts[i];
                    }
                }
            }
        }

        int longest = 0, ans = 0;
        for (int i = 0; i < len; i++) {
            longest = Math.max(longest, lengths[i]);
        }

        for (int j = 0; j < len; j++) {
            if (lengths[j] == longest) {
                ans += counts[j];
            }
        }
        return ans;
    }
    /**
     * 方法一：动态规划
     * 算法：
     * 假设对于以 nums[i] 结尾的序列，我们知道最长序列的长度 length[i]，以及具有该长度的序列的 count[i]。
     * 对于每一个 i<j 和一个 A[i]<A[j]，我们可以将一个 A[j] 附加到以 A[i] 结尾的最长子序列上。
     * 如果这些序列比 length[j] 长，那么我们就知道我们有count[i] 个长度为 length 的序列。
     * 如果这些序列的长度与 length[j] 相等，那么我们就知道现在有 count[i] 个额外的序列（即 count[j]+=count[i]）。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/solution/zui-chang-di-zeng-zi-xu-lie-de-ge-shu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
