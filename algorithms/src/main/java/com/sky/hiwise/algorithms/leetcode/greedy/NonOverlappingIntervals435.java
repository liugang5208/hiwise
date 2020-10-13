package com.sky.hiwise.algorithms.leetcode.greedy;

import java.util.Arrays;

public class NonOverlappingIntervals435 {

    /**
     * 435. 无重叠区间
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     * 注意:
     * 可以认为区间的终点总是大于它的起点。
     * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     * 示例 1:
     * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
     * 输出: 1
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     * 示例 2:
     * 输入: [ [1,2], [1,2], [1,2] ]
     * 输出: 2
     * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
     * 示例 3:
     * 输入: [ [1,2], [2,3] ]
     * 输出: 0
     * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (v1, v2) -> {
            if (v1[0] != v2[0]) {
                return v1[0] > v2[0] ? 1 : -1;
            }
            return v1[1] > v2[1] ? 1 : -1;
        });
        for(int i = 0; i < intervals.length; i++) {
            for(int j = 0; j < intervals[i].length; j++) {
                System.out.println(intervals[i][j]);
            }
        }
        //memo[i]表示使用intervals[0...i]的区间能构成的最长不重叠区间序列
        int[] memo = new int[intervals.length];
        Arrays.fill(memo, 1);
        for(int i = 1; i < intervals.length; i++) {
            for(int j = 0; j < i; j++) {
                if (intervals[i][0] >= intervals[j][1]) {
                    memo[i] = Math.max(memo[i], 1 + memo[j]);
                }
            }
        }
        int res = 0;
        for(int i = 0; i < memo.length; i++) {
            res = Math.max(res, memo[i]);
        }
        return intervals.length - res;
    }

    /**
     * 区间结尾不等时 谁的结尾越小排在前面 否则相等时 谁的开始越小排在前面
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (v1, v2) -> {
            if (v1[1] != v2[1]) {
                return v1[1] - v2[1];
            }
            return v1[0] - v2[0];
        });

        int res = 1;
        int pre = 0;
        //[[1,2],[1,3],[2,3],[3,4]]
        for(int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= intervals[pre][1]) {
                res ++;
                pre = i;
            }
        }
        return intervals.length - res;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,2}, {2,3}, {3,4}, {1,3}, {2,4}};
        int res = (new NonOverlappingIntervals435()).eraseOverlapIntervals2(intervals);
        System.out.println(res);
    }

}
