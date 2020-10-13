package com.sky.hiwise.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class ContinuousSequenceSum5702 {

    /**
     * 面试题57 - II. 和为s的连续正数序列
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     * 示例 1：
     * 输入：target = 9
     * 输出：[[2,3,4],[4,5]]
     * 示例 2：
     * 输入：target = 15
     * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        if (target <= 1) {
            return new int[0][0];
        }
        List<int[]> ans = new ArrayList<>();
        int start = 1;
        int end = 1;
        int sum = 0;
        while(start <= target / 2) {
            if (sum < target) {
                sum += end;
                end++;
            } else if (sum > target) {
                sum -= start;
                start ++;
            } else {
                int[] temp = new int[end- start];
                for (int i = 0; i < end - start; i++) {
                    temp[i] = start + i;
                }
                ans.add(temp);
                sum -= start;
                start ++;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
