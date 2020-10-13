package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {
        int[] nums = new int[]{9,8,7,6,5,4,3,2,1};
        (new ShellSort()).insertSort(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{9,8,7,6,5,4,3,2,1};
        (new ShellSort()).shellSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void shellSort(int[] nums) {
        int dk = nums.length / 2;
        while (dk >= 1) {
            shellInsertSort(nums, dk);
            dk = dk / 2;
        }
    }

    public void shellInsertSort(int[] nums, int dk) {
        for (int i = dk; i < nums.length; i++) {
            if (nums[i] < nums[i - dk]) {
                int temp = nums[i];
                nums[i] = nums[i - dk];
                int j;
                for (j = i - dk; j >= 0 && nums[j] > temp ; j = j - dk) {
                    nums[j + dk] = nums[j];
                }
                nums[j + dk] = temp;
            }
        }
    }

    public void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                int temp = nums[i];
                for(int j = i; j >= 0; j--) {
                    if (j > 0 && nums[j - 1] > temp) {
                        nums[j] = nums[j - 1];
                    } else {
                        nums[j] = temp;
                        break;
                    }
                }
            }
        }
    }

    /**
     *   int temp;
     *
     *         for (int i=1;i<arr.length;i++){
     *
     *             //待排元素小于有序序列的最后一个元素时，向前插入
     *             if (arr[i]<arr[i-1]){
     *                 temp = arr[i];
     *                 for (int j=i;j>=0;j--){
     *                     if (j>0 && arr[j-1]>temp) {
     *                         arr[j]=arr[j-1];
     *                     }else {
     *                         arr[j]=temp;
     *                         break;
     *                     }
     *                 }
     *             }
     *         }
     *
     *         System.out.println(Arrays.toString(arr));
     */
}
