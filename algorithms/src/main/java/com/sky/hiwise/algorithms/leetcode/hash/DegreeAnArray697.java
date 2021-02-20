package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

public class DegreeAnArray697 {
    /**
     * 697. 数组的度
     * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
     * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     * 示例 1：
     * 输入：[1, 2, 2, 3, 1]
     * 输出：2
     * 解释：
     * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
     * 连续子数组里面拥有相同度的有如下所示:
     * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
     * 最短连续子数组[2, 2]的长度为2，所以返回2.
     * 示例 2：
     * 输入：[1,2,2,3,1,4,2]
     * 输出：6
     * 提示：
     * nums.length 在1到 50,000 区间范围内。
     * nums[i] 是一个在 0 到 49,999 范围内的整数。
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        int maxNum = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            if (maxNum < arr[0]) {
                maxNum = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (maxNum == arr[0]) {
                minLen = Math.min(minLen, arr[2] - arr[1] + 1);
            }
        }
        return minLen;
    }
    /**
     * 方法一：哈希表
     * 思路及解法
     * 记原数组中出现次数最多的数为 xx，那么和原数组的度相同的最短连续子数组，必然包含了原数组中的全部 xx，且两端恰为 xx 第一次出现和最后一次出现的位置。
     * 因为符合条件的 xx 可能有多个，即多个不同的数在原数组中出现次数相同。所以为了找到这个子数组，我们需要统计每一个数出现的次数，同时还需要统计每一个数第一次出现和最后一次出现的位置。
     * 在实际代码中，我们使用哈希表实现该功能，每一个数映射到一个长度为 33 的数组，数组中的三个元素分别代表这个数出现的次数、这个数在原数组中第一次出现的位置和这个数在原数组中最后一次出现的位置。
     * 当我们记录完所有信息后，我们需要遍历该哈希表，找到元素出现次数最多，且前后位置差最小的数。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/degree-of-an-array/solution/shu-zu-de-du-by-leetcode-solution-ig97/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
