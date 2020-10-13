package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] nums = {4, 46,61,21,7,8,3,2,90,2,25,56,2,111,134,2,6,7,3,5,255};
        sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public static void sort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;// (right - left) / 2 + left
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        //近乎有序数组归并排序优化
        if (nums[mid] > nums[mid  + 1]) {
            merge(nums, left, mid, right);
        }
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int[] aux = Arrays.copyOfRange(nums, left, right + 1);
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                // 如果左半部分元素已经全部处理完毕
                nums[k] = aux[j - left];
                j++;
            } else if (j > right) {
                // 如果右半部分元素已经全部处理完毕
                nums[k] = aux[i - left];
                i++;
            } else if (aux[i - left] < aux[j - left]) {
                // 左半部分所指元素 < 右半部分所指元素
                nums[k] = aux[i - left];
                i++;
            } else {
                // 左半部分所指元素 >= 右半部分所指元素
                nums[k] = aux[j - left];
                j++;
            }
        }
    }
}
