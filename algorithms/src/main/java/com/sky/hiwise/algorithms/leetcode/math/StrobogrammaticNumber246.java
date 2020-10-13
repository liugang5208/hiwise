package com.sky.hiwise.algorithms.leetcode.math;

public class StrobogrammaticNumber246 {

    /**
     * [LeetCode] 246. Strobogrammatic Number 对称数
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
     * Write a function to determine if a number is strobogrammatic.
     * The number is represented as a string.
     * Example 1:
     * Input:  "69"
     * Output: true
     * Example 2:
     * Input:  "88"
     * Output: true
     * Example 3:
     * Input:  "962"
     * Output: false
     * @param num
     * @return
     */
    public boolean isStrobogrammatic(String num) {
        char[] nums = num.toCharArray();
        int left = 0, right = nums.length;
        while (left < right) {
            if (nums[left] == nums[right]) {
                if (nums[left] != '1' && nums[left] != '0'  && nums[left] != '8' ) {
                    return false;
                }
            } else {
                if ((nums[left] != '6' ||  nums[right] != '9') && (nums[left] != '9' ||  nums[right] != '6')) {
                    return false;
                }
            }
            left++;
            right--;
        }
        return true;
    }
    /**
     * 这道题定义了一种对称数，就是说一个数字旋转 180 度和原来一样，也就是倒过来看一样，
     * 比如 609，倒过来还是 609 等等，满足这种条件的数字其实没有几个，
     * 只有 0,1,8,6,9。这道题其实可以看做求回文数的一种特殊情况，还是用双指针来检测，
     * 首尾两个数字如果相等的话，只有它们是 0,1,8 中间的一个才行，
     * 如果它们不相等的话，必须一个是6一个是9，或者一个是9一个是6，
     * 其他所有情况均返回 false，参见代码如下；
     */

    /**
     * 由于满足题意的数字不多，所以可以用 HashMap 来做，把所有符合题意的映射都存入哈希表中，
     * 然后双指针扫描，看对应位置的两个数字是否在哈希表里存在映射，若不存在，返回 false，
     * 遍历完成返回 true，参见代码如下：
     * class Solution {
     * public:
     *     bool isStrobogrammatic(string num) {
     *         unordered_map<char, char> m {{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
     *         for (int i = 0; i <= num.size() / 2; ++i) {
     *             if (m[num[i]] != num[num.size() - i - 1]) return false;
     *         }
     *         return true;
     *     }
     * };
     */

}
