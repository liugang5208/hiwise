package com.sky.hiwise.algorithms.leetcode.array;

import java.util.LinkedList;

public class InsertInterval57 {

    /**
     * 57. 插入区间
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     * 示例 1:
     * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出: [[1,5],[6,9]]
     * 示例 2:
     * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出: [[1,2],[3,10],[12,16]]
     * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> ans = new LinkedList<>();
        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        int idx = 0;
        while (idx < intervals.length && intervals[idx][0] < newStart) {
            ans.add(intervals[idx++]);
        }

        int[] interval = new int[2];
        if (ans.isEmpty() || ans.getLast()[1] < newStart) {
            ans.add(newInterval);
        } else {
            interval = ans.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            ans.add(interval);
        }

        while(idx < intervals.length) {
            if (intervals[idx][0] > ans.getLast()[1]) {
                ans.add(intervals[idx]);
            } else {
                interval = ans.removeLast();
                interval[1] = Math.max(interval[1], intervals[idx][1]);
                ans.add(interval);
            }
            idx++;
        }

        return ans.toArray(new int[ans.size()][2]);
    }
}
