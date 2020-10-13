package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.Arrays;

public class InversionCount {
    public static void main(String[] args) {
        int[] nums = {4, 46,61,21};
        System.out.println("InversionCount:" + solve(nums));
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public static int solve(int[] nums) {
        return solve(nums, 0, nums.length - 1);
    }

    public static int solve(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = (right - left) / 2 + left;
        int leftCount = solve(nums, left, mid);
        int rightCount = solve(nums, mid + 1, right);
        return leftCount + rightCount + merge(nums, left, mid, right);
//        if (nums[mid] > nums[mid+1] ) {
//            return leftCount + rightCount + merge(nums, left, mid, right);
//        }
//        return leftCount + rightCount;
    }

    private static int merge(int[] nums, int left, int mid, int right) {
        int res = 0;
        int[] aux = Arrays.copyOfRange(nums, left, right + 1);
        int i = left, j = mid + 1;
        for(int k = left; k <= right; k++) {
            if (i > mid) {
                nums[k] = aux[j - left];
                j++;
            } else if (j > right) {
                nums[k] = aux[i - left];
                i++;
            } else if (aux[i - left] <= aux[j - left]) {
                nums[k] = aux[i - left];
                i++;
            } else {
                nums[k] = aux[j - left];
                j++;
                // 此时, 因为右半部分k所指的元素小
                // 这个元素和左半部分的所有未处理的元素都构成了逆序数对
                // 左半部分此时未处理的元素个数为 mid - i + 1
                res += (long)(mid - i + 1);
            }
        }
        return res;
    }

}
