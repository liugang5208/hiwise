package com.sky.hiwise.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeIntervals56 {

    /**
     * 56. 合并区间
     * 给出一个区间的集合，请合并所有重叠的区间。
     * 示例 1:
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2:
     * 输入: [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> ans = new LinkedList<>();
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        for (int[] interval : intervals) {
            if (ans.isEmpty() || ((LinkedList<int[]>) ans).getLast()[1] < interval[0]) {
                ans.add(interval);
            } else {
                ((LinkedList<int[]>) ans).getLast()[1] = Math.max(((LinkedList<int[]>) ans).getLast()[1], interval[1]);
            }
        }

        int[][] res = new int[ans.size()][2];
        for(int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);

        }
        return res;
    }
}

