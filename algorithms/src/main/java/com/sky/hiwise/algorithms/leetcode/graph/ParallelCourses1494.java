package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class ParallelCourses1494 {

    /**
     * 1494. 并行课程 II
     * 给你一个整数 n 表示某所大学里课程的数目，编号为 1 到 n ，数组 dependencies 中， dependencies[i] = [xi, yi]  表示一个先修课的关系，
     * 也就是课程 xi 必须在课程 yi 之前上。同时你还有一个整数 k 。
     * 在一个学期中，你 最多 可以同时上 k 门课，前提是这些课的先修课在之前的学期里已经上过了。
     * 请你返回上完所有课最少需要多少个学期。题目保证一定存在一种上完所有课的方式。
     * 示例 1：
     * 输入：n = 4, dependencies = [[2,1],[3,1],[1,4]], k = 2
     * 输出：3
     * 解释：上图展示了题目输入的图。在第一个学期中，我们可以上课程 2 和课程 3 。然后第二个学期上课程 1 ，第三个学期上课程 4 。
     * 输入：n = 5, dependencies = [[2,1],[3,1],[4,1],[1,5]], k = 2
     * 输出：4
     * 解释：上图展示了题目输入的图。一个最优方案是：第一学期上课程 2 和 3，第二学期上课程 4 ，第三学期上课程 1 ，第四学期上课程 5 。
     */
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        TreeSet<Integer>[] graph = new TreeSet[n + 1];
        int[] indegrees = new int[n + 1];
        for (int[] dep : dependencies) {
            if (graph[dep[0]] == null) {
                graph[dep[0]] = new TreeSet<>();
            }
            graph[dep[0]].add(dep[1]);
            indegrees[dep[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer curr = queue.poll();
                if (graph[curr] != null) {
                    for (Integer next : graph[curr]) {
                        indegrees[next]--;
                        if (indegrees[next] == 0) {
                            queue.add(next);
                        }
                    }
                }
            }
            if (size <= k) {
                ans ++;
            } else {
                ans += size / k + (size % k == 0 ? 0 : 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        /**
         * 52 / 67 个通过测试用例
         12
         [[1,2],[1,3],[7,5],[7,6],[4,8],[8,9],[9,10],[10,11],[11,12]]
         2
         */
        int n = 12, k = 2;
        int[][] dependencies = new int[][]{{1,2},{1,3},{7,5},{7,6},{4,8},{8,9},{9,10},{10,11},{11,12}};//new int[][]{{1,2},{4,2}};
        System.out.println((new ParallelCourses1494()).minNumberOfSemesters(n, dependencies, k));
    }
}
