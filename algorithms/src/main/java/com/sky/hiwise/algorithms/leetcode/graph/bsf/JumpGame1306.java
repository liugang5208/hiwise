package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.LinkedList;
import java.util.Queue;

public class JumpGame1306 {

    /**
     * 1306. 跳跃游戏 III
     * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
     * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
     * 注意，不管是什么情况下，你都无法跳到数组之外。
     * 示例 1：
     * 输入：arr = [4,2,3,0,3,1,2], start = 5
     * 输出：true
     * 解释：
     * 到达值为 0 的下标 3 有以下可能方案：
     * 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
     * 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
     * 示例 2：
     * 输入：arr = [4,2,3,0,3,1,2], start = 0
     * 输出：true
     * 解释：
     * 到达值为 0 的下标 3 有以下可能方案：
     * 下标 0 -> 下标 4 -> 下标 1 -> 下标 3
     * @param arr
     * @param start
     * @return
     */
    public boolean canReach(int[] arr, int start) {
        if (arr[start] == 0) {
            return true;
        }
        int len = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[len];
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            if (curr + arr[curr] < len && !visited[curr + arr[curr]]) {
                if (arr[curr + arr[curr]] == 0) {
                    return true;
                }
                queue.add(curr + arr[curr]);
                visited[curr + arr[curr]] = true;
            }
            if (curr - arr[curr] >= 0 && !visited[curr - arr[curr]]) {
                if (arr[curr - arr[curr]] == 0) {
                    return true;
                }
                queue.add(curr - arr[curr]);
                visited[curr - arr[curr]] = true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,2,3,0,3,1,2};
        int start = 5;
        System.out.println((new JumpGame1306()).canReach(arr, start));
    }
}
