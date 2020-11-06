package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.Arrays;

public class SpecialArrayWithXElements1608 {
    /**
     * 1608. 特殊数组的特征值
     * 给你一个非负整数数组 nums 。如果存在一个数 x ，使得 nums 中恰好有 x 个元素 大于或者等于 x ，那么就称 nums 是一个 特殊数组 ，而 x 是该数组的 特征值 。
     * 注意： x 不必 是 nums 的中的元素。
     * 如果数组 nums 是一个 特殊数组 ，请返回它的特征值 x 。否则，返回 -1 。可以证明的是，如果 nums 是特殊数组，那么其特征值 x 是 唯一的 。
     * 示例 1：
     * 输入：nums = [3,5]
     * 输出：2
     * 解释：有 2 个元素（3 和 5）大于或等于 2 。
     * 示例 2：
     * 输入：nums = [0,0]
     * 输出：-1
     * 解释：没有满足题目要求的特殊数组，故而也不存在特征值 x 。
     * 如果 x = 0，应该有 0 个元素 >= x，但实际有 2 个。
     * 如果 x = 1，应该有 1 个元素 >= x，但实际有 0 个。
     * 如果 x = 2，应该有 2 个元素 >= x，但实际有 0 个。
     * x 不能取更大的值，因为 nums 中只有两个元素。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/special-array-with-x-elements-greater-than-or-equal-x
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        if (nums.length <= nums[0]) {
            return nums.length;
        }
        int x = nums.length - 1, i = 1;
        while (x > 0) {
            if (x <= nums[i] && x > nums[i - 1]) {
                return x;
            } else {
                x--;
                i++;
            }
        }
        return -1;
    }
    /**
     * 思路就是 x 的取值范围只可能是数组长度 nums.length 到 1之间；
     * 所以先将数组排序，然后枚举 x 是否满足题意。
     * 是否满足题意的判断方式是看数组 x 位置之后的元素是否大于等于 x 和数组 x 位置之前的元素是否小于 x。
     * 满足返回 x，都不满足则返回 -1。
     * 作者：shen-ying-shi-zhe-2
     * 链接：https://leetcode-cn.com/problems/special-array-with-x-elements-greater-than-or-equal-x/solution/java-xian-pai-xu-zai-mei-ju-x-de-ke-neng-zhi-by-sh/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
