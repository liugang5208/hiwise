package com.sky.hiwise.algorithms.leetcode.array;

import java.util.HashMap;

public class ContinuousSubarraySum523 {

    /**
     * 523. 连续的子数组和
     * 给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
     *
     * 示例 1:
     *
     * 输入: [23,2,4,6,7], k = 6
     * 输出: True
     * 解释: [2,4] 是一个大小为 2 的子数组，并且和为 6。
     * 示例 2:
     *
     * 输入: [23,2,6,4,7], k = 6
     * 输出: True
     * 解释: [23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
     * 说明:
     *
     * 数组的长度不会超过10,000。
     * 你可以认为所有数字总和在 32 位有符号整数范围内。
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }

            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }
    /**
     * 在这种方法中，我们使用 HashMap 来保存到第 ii 个元素为止的累积和，但我们对这个前缀和除以 kk 取余数。原因如下：
     * 我们遍历一遍给定的数组，记录到当前位置为止的 sum%ksum 。
     * 一旦我们找到新的 sum%ksum 的值（即在 HashMap 中没有这个值），我们就往 HashMap 中插入一条记录 (sum%k, i)(sum 。
     * 现在，假设第 ii 个位置的 sum%ksum 的值为 remrem 。如果以 ii 为左端点的任何子数组的和是 kk 的倍数，比方说这个位置为 jj ，
     * 那么 HashMap 中第 jj 个元素保存的值为 (rem + n*k)\%k(rem+n∗k)%k ，其中 nn 是某个大于 0 的整数。
     * 我们会发现 (rem + n*k)\%k = rem(rem+n∗k)%k=rem ，也就是跟第 ii 个元素保存到 HashMap 中的值相同。
     * 基于这一观察，我们得出结论：无论何时，只要 sum%ksum 的值已经被放入 HashMap 中了，代表着有两个索引 ii 和 jj ，
     * 它们之间元素的和是 kk 的整数倍。因此，只要 HashMap 中有相同的 sum\%ksum%k ，我们就可以直接返回 \teat{True} 。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum/solution/lian-xu-de-zi-shu-zu-he-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
