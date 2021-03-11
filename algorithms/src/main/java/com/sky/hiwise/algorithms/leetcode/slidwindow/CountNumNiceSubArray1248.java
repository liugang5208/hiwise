package com.sky.hiwise.algorithms.leetcode.slidwindow;

public class CountNumNiceSubArray1248 {
    /**
     * 1248. 统计「优美子数组」
     * 给你一个整数数组 nums 和一个整数 k。
     * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
     * 请返回这个数组中「优美子数组」的数目。
     * 示例 1：
     * 输入：nums = [1,1,2,1,1], k = 3
     * 输出：2
     * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
     * 示例 2：
     * 输入：nums = [2,4,6], k = 1
     * 输出：0
     * 解释：数列中不包含任何奇数，所以不存在优美子数组。
     * 示例 3：
     * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
     * 输出：16
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int left = 0, right = 0, oddCnt = 0, ans = 0;
        while (right < nums.length) {
            if ((nums[right++] & 1) == 1) {
                oddCnt++;
            }
            if (oddCnt == k) {
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0) {
                    right++;
                }
                int rightEvenCnt = right - tmp;
                int leftEvenCnt = 0;
                while ((nums[left] & 1) == 0) {
                    leftEvenCnt++;
                    left++;
                }
                ans += (rightEvenCnt + 1) * (leftEvenCnt + 1);
                left++;
                oddCnt--;
            }
        }
        return ans;
    }

    /**
     * 一、滑动窗口
     * 不断右移 right 指针来扩大滑动窗口，使其包含 k 个奇数；
     * 若当前滑动窗口包含了 k 个奇数，则如下「计算当前窗口的优美子数组个数」：
     * 统计第 1 个奇数左边的偶数个数 leftEvenCnt。 这 leftEvenCnt 个偶数都可以作为「优美子数组」的起点，
     * 因此起点的选择有 leftEvenCnt + 1 种（因为可以一个偶数都不取，因此别忘了 +1 喔）。
     * 统计第 k 个奇数右边的偶数个数 rightEvenCnt 。 这 rightEvenCnt 个偶数都可以作为「优美子数组」的终点，
     * 因此终点的选择有 rightEvenCnt + 1 种（因为可以一个偶数都不取，因此别忘了 +1 喔）。
     * 因此「优美子数组」左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)。
     * 作者：sweetiee
     * 链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays/solution/hua-dong-chuang-kou-qian-zhui-he-bi-xu-miao-dong-b/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
