package com.sky.hiwise.algorithms.leetcode.graph.toposort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class CourseSchedule1462 {
    /**
     *1462. 课程安排 IV
     * 你总共需要上 n 门课，课程编号依次为 0 到 n-1 。
     * 有的课会有直接的先修课程，比如如果想上课程 0 ，你必须先上课程 1 ，那么会以 [1,0] 数对的形式给出先修课程数对。
     * 给你课程总数 n 和一个直接先修课程数对列表 prerequisite 和一个查询对列表 queries 。
     * 对于每个查询对 queries[i] ，请判断 queries[i][0] 是否是 queries[i][1] 的先修课程。
     * 请返回一个布尔值列表，列表中每个元素依次分别对应 queries 每个查询对的判断结果。
     * 注意：如果课程 a 是课程 b 的先修课程且课程 b 是课程 c 的先修课程，那么课程 a 也是课程 c 的先修课程。
     * 示例 1：
     * 输入：n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
     * 输出：[false,true]
     * 解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
     */
    int[][] memo;
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        TreeSet<Integer>[] adj = new TreeSet[n];
        for (int[] pre : prerequisites) {
            if (adj[pre[1]] == null) {
                adj[pre[1]] = new TreeSet<>();
            }
            adj[pre[1]].add(pre[0]);
        }
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            if (canReach(adj, queries[i][1], queries[i][0])) {
                ans.add(i, true);
            } else {
                ans.add(i, false);
            }
        }
        return ans;
    }

    //memo 记忆化存储 避免多余不必要的递归
    private boolean canReach(TreeSet<Integer>[] adj, int idx, int target) {
        if (memo[idx][target] > 0) {
            return memo[idx][target] == 1;
        }
        if(adj[idx] != null) {
            for (Integer item : adj[idx]) {
                if (item == target || canReach(adj, item, target)) {
                    memo[idx][target] = 1;
                   return true;
                }
            }
        }
        memo[idx][target] = 2;
        return false;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] prerequisites = new int[][]{{0,1},{1,2},{2,3},{3,4}};
        int[][] queries = new int[][]{{0,4},{4,0},{1,3},{3,0}};
        System.out.println((new CourseSchedule1462()).checkIfPrerequisite(n, prerequisites, queries));
    }
}
