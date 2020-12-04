package com.sky.hiwise.algorithms.leetcode.greedy;

import java.util.Stack;

public class CreateMaxNumber321 {
    /**
     * 321. 拼接最大数
     * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
     * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
     * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
     * 说明: 请尽可能地优化你算法的时间和空间复杂度。
     * 示例 1:
     * 输入:
     * nums1 = [3, 4, 6, 5]
     * nums2 = [9, 1, 2, 5, 8, 3]
     * k = 5
     * 输出:
     * [9, 8, 6, 5, 3]
     * 示例 2:
     * 输入:
     * nums1 = [6, 7]
     * nums2 = [6, 0, 4]
     * k = 5
     * 输出:
     * [6, 7, 6, 0, 4]
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2= nums2.length;
        int[] ans = new int[k];
        int start = Math.max(0, k - len2), end = Math.min(len1, k);
        for (int i = start; i <= end; i++) {
            int[] subNum1 = pickMax(nums1, i);
            int[] subNum2 = pickMax(nums2, k - i);
            int[] merged = merge(subNum1, subNum2);
            if (compare(merged, 0, ans, 0) > 0) {
                System.arraycopy(merged, 0, ans, 0, k);
            }
        }
        return ans;
    }

    public int[] pickMax(int[] nums, int k) {
        int len = nums.length;
        Stack<Integer> stack = new Stack<>();
        int drop = len - k;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            while(!stack.isEmpty() && drop > 0 && stack.peek() < num) {
               stack.pop();
               drop--;
            }
            stack.add(num);
        }
        int[] ans = new int[stack.size()];
        int idx = 0;
        for(Integer s : stack) {
            ans[idx++] = s;
        }
        return ans;
    }

    public int[] merge(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0) {
            return nums2;
        }
        if (len2 == 0) {
            return nums1;
        }
        int maxLen = len1 + len2;
        int[] merged = new int[maxLen];
        int idx1 = 0, idx2 = 0;
        for (int i = 0; i < maxLen; i++) {
            if (compare(nums1, idx1, nums2, idx2) > 0) {
                merged[i] = nums1[idx1++];
            } else {
                merged[i] = nums2[idx2++];
            }
        }
        return merged;
    }

    public int compare(int[] nums1, int idx1, int[] nums2, int idx2) {
        int len1 = nums1.length, len2 = nums2.length;
        while(idx1 < len1 && idx2 < len2) {
            int diff = nums1[idx1] - nums2[idx2];
            if (diff != 0) {
                return diff;
            }
            idx1++;
            idx2++;
        }
        return (len1 - idx1) - (len2 - idx2);
    }
    /**
     * 思路
     * 和第一道题类似，只不不过这一次是两个数组，而不是一个，并且是求最大数。
     * 最大最小是无关紧要的，关键在于是两个数组，并且要求从两个数组选取的元素个数加起来一共是 k。
     * 然而在一个数组中取 k 个数字，并保持其最小（或者最大），我们已经会了。但是如果问题扩展到两个，会有什么变化呢？
     * 实际上，问题本质并没有发生变化。 假设我们从 nums1 中取了 k1 个，从 num2 中取了 k2 个，其中 k1 + k2 = k。而 k1 和 k2 这 两个子问题我们是会解决的。由于这两个子问题是相互独立的，因此我们只需要分别求解，然后将结果合并即可。
     * 假如 k1 和 k2 个数字，已经取出来了。那么剩下要做的就是将这个长度分别为 k1 和 k2 的数字，合并成一个长度为 k 的数组合并成一个最大的数组。
     * 以题目的 nums1 = [3, 4, 6, 5] nums2 = [9, 1, 2, 5, 8, 3] k = 5 为例。 假如我们从 num1 中取出 1 个数字，那么就要从 nums2 中取出 4 个数字。
     * 运用第一题的方法，我们计算出应该取 nums1 的 [6]，并取 nums2 的 [9,5,8,3]。 如何将 [6] 和 [9,5,8,3]，使得数字尽可能大，并且保持相对位置不变呢？
     * 实际上这个过程有点类似归并排序中的治，而上面我们分别计算 num1 和 num2 的最大数的过程类似归并排序中的分。
     * 具体算法：
     * 从 nums1 中 取 min(i, len(nums1))min(i,len(nums1)) 个数形成新的数组 A（取的逻辑同第一题），其中 i 等于 0,1,2, ... k。
     * 从 nums2 中 对应取 min(j, len(nums2))min(j,len(nums2)) 个数形成新的数组 B（取的逻辑同第一题），其中 j 等于 k - i。
     * 将 A 和 B 按照上面的 merge 方法合并
     * 上面我们暴力了 k 种组合情况，我们只需要将 k 种情况取出最大值即可。
     * 作者：fe-lucifer
     * 链接：https://leetcode-cn.com/problems/create-maximum-number/solution/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

}
