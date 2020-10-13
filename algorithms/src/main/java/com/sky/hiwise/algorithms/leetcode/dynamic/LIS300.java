package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;

public class LIS300 {

    /**
     * 300. 最长上升子序列 最长递增子序列
     * longest-increasing-subsequence
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * 示例:
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:
     * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     * 你算法的时间复杂度应该为 O(n2) 。
     * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] memo = new int[nums.length];
        Arrays.fill(memo, 1);
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++ ) {
                if (nums[i] > nums[j]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                    res = Math.max(res, memo[i]);
                }
            }
        }
        return res;
    }
    /**
     * LIS(i)表示以第i个数结尾的最长上升子序列的长度
     * 表示[0...i]范围内选择 nums[i]可以获得的最长上升子序列的长度
     * LIS(i) = max(1 + LIS(j) if nums[i] > nums[j]) (j < i)
     */

    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] tails = new int[len];
        int res = 0;
        for (int i = 0; i < len; i++) {
            int start = 0, end = res;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (tails[mid] < nums[i]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            tails[start] = nums[i];
            if (res == end) {
                res ++;
            }
        }
        return res;
    }
    /**
     * 解法二：动态规划 + 二分查找
     * 设计思路：
     * 新的状态定义：
     * 我们考虑维护一个列表 tails，其中每个元素 tails[k] 的值代表 长度为 k+1 的子序列尾部元素的值。
     * 如 [1,4,6] 序列，长度为 1,2,3 的子序列尾部元素值分别为 tails = [1,4,6]。
     * 状态转移设计：
     * 设常量数字 N，和随机数字 x，我们可以容易推出：当 N 越小时，N<x 的几率越大。例如： N=0 肯定比 N=1000 更可能满足 N<x。
     * 在遍历计算每个 tails[k]，不断更新长度为 [1,k] 的子序列尾部元素值，始终保持每个尾部元素值最小
     * （例如 [1,5,3]， 遍历到元素 5 时，长度为 2 的子序列尾部元素值为 5；当遍历到元素 3 时，尾部元素值应更新至 3，因为 3 遇到比它大的数字的几率更大）。
     * tails列表一定是严格递增的： 即当尽可能使每个子序列尾部元素值最小的前提下，子序列越长，其序列尾部元素值一定更大。
     * 反证法证明： 当 k < i，若 tails[k] >= tails[i]，代表较短子序列的尾部元素的值 > 较长子序列的尾部元素的值。这是不可能的，因为从长度为 i 的子序列尾部倒序删除 i−1 个元素，剩下的为长度为 k 的子序列，设此序列尾部元素值为 v，则一定有v<tails[i] （即长度为 k 的子序列尾部元素值一定更小）， 这和 tails[k]>=tails[i] 矛盾。
     * 既然严格递增，每轮计算 tails[k] 时就可以使用二分法查找需要更新的尾部元素值的对应索引 i。
     * 算法流程：
     * 状态定义：
     * tails[k] 的值代表 长度为 k+1 子序列 的尾部元素值。
     * 转移方程： 设 res 为 tails 当前长度，代表直到当前的最长上升子序列长度。设 j∈[0,res)，考虑每轮遍历 nums[k] 时，通过二分法遍历 [0,res) 列表区间，找出 nums[k] 的大小分界点，会出现两种情况：
     * 区间中存在 tails[i] > nums[k] ： 将第一个满足 tails[i] > nums[k] 执行 tails[i] = nums[k] ；因为更小的 nums[k] 后更可能接一个比它大的数字（前面分析过）。
     * 区间中不存在 tails[i] > nums[k] ： 意味着 nums[k] 可以接在前面所有长度的子序列之后，因此肯定是接到最长的后面（长度为 res ），新子序列长度为 res + 1。
     * 初始状态：
     * 令 tails 列表所有值 =0。
     * 返回值：
     * 返回 res ，即最长上升子子序列长度。
     * 复杂度分析：
     * 时间复杂度 O(NlogN) ： 遍历 nums 列表需 O(N)，在每个 nums[i] 二分法需 O(logN)。
     * 空间复杂度 O(N) ： tails 列表占用线性大小额外空间。
     */

}
