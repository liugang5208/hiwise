package com.sky.hiwise.algorithms.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinAbsoluteDifference1200 {

    /**
     * 1200. 最小绝对差
     * 给你个整数数组 arr，其中每个元素都 不相同。
     * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
     * 示例 1：
     * 输入：arr = [4,2,1,3]
     * 输出：[[1,2],[2,3],[3,4]]
     * 示例 2：
     * 输入：arr = [1,3,6,10,15]
     * 输出：[[1,3]]
     * 示例 3：
     * 输入：arr = [3,8,-10,23,19,-4,-14,27]
     * 输出：[[-14,-10],[19,23],[23,27]]
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        int dist = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int j = i + 1;
            if (arr[j] - arr[i] == dist) {
                ans.add(Arrays.asList(arr[i], arr[j]));
            } else if (arr[j] - arr[i] < dist) {
                ans = new ArrayList<>();
                ans.add(Arrays.asList(arr[i], arr[j]));
                dist = arr[j] - arr[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,2,1,3};
        System.out.println((new MinAbsoluteDifference1200()).minimumAbsDifference(arr));
    }
}
