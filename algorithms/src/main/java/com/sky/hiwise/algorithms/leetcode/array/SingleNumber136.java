package com.sky.hiwise.algorithms.leetcode.array;

import java.util.concurrent.atomic.AtomicInteger;

public class SingleNumber136 {

    public static void main(String[] args) {
//        SingleNumber136 sn = new SingleNumber136();
//        int []nums = {1,1,45,56,67,56,45};
//        System.out.println(sn.singleNumber(nums));
        char[] arr1 = {'a', 'b', 'c', 'd', 'e', 'f'};
        char[] arr2 = {'a', 'b', 'c',  'e', 'd'};
        System.out.println(single(arr1, arr2));

    }

    /**
     * 136. 只出现一次的数字 扩展 137  260
     给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     示例 1:  输入: [2,2,1]  输出: 1
     示例 2:  输入: [4,1,2,1,2]  输出: 4
     * @param arr
     * @return
     */
    public int singleNumber(int[] arr) {
        if (arr.length == 0)
            return -1;
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result = result ^ arr[i];
        }
        return result;
    }

    public static char single(char[] chars1, char[] chars2) {
        int i = 1, j = 0;
        int ans = chars1[0];
        while(i < chars1.length && j < chars1.length) {
            ans = ans ^ chars1[i] ^ chars2[j];
            i++;
            j++;
        }
        while(i < chars1.length) {
            ans = ans ^ chars1[i];
            i++;
        }
        while(j < chars2.length) {
            ans = ans ^ chars2[j];
            j++;
        }
        return (char)ans;
    }

    /**
     * 面试题56 - I. 数组中数字出现的次数
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     * 示例 1：
     * 输入：nums = [4,1,4,6]
     * 输出：[1,6] 或 [6,1]
     * 示例 2：
     * 输入：nums = [1,2,10,4,1,4,3,3]
     * 输出：[2,10] 或 [10,2]
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }

        int div = 1;
        while((div & result) == 0) {
            div <<= 1;
        }
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & div) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    /**
     * 方法一：分组异或
     * 思路
     * 让我们先来考虑一个比较简单的问题：
     * 如果除了一个数字以外，其他数字都出现了两次，那么如何找到出现一次的数字？
     * 答案很简单：全员进行异或操作即可。考虑异或操作的性质：对于两个操作数的每一位，相同结果为 00，不同结果为 11。
     * 那么在计算过程中，成对出现的数字的所有位会两两抵消为 00，最终得到的结果就是那个出现了一次的数字。
     * 那么这一方法如何扩展到找出两个出现一次的数字呢？
     * 如果我们可以把所有数字分成两组，使得：
     * 两个只出现一次的数字在不同的组中；
     * 相同的数字会被分到相同的组中。
     * 那么对两个组分别进行异或操作，即可得到答案的两个数字。这是解决这个问题的关键。
     * 算法
     * 先对所有数字进行一次异或，得到两个出现一次的数字的异或值。
     * 在异或结果中找到任意为 11 的位。
     * 根据这一位对所有的数字进行分组。
     * 在每个组内进行异或操作，得到两个数字。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

}
