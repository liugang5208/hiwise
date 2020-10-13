package com.sky.hiwise.algorithms.leetcode.graph;

public class FindTownJudge997 {
    /**
     * 997. 找到小镇的法官
     * 在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。
     * 如果小镇的法官真的存在，那么：
     * 小镇的法官不相信任何人。
     * 每个人（除了小镇法官外）都信任小镇的法官。
     * 只有一个人同时满足属性 1 和属性 2 。
     * 给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。
     * 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。
     * 示例 1：
     * 输入：N = 2, trust = [[1,2]]
     * 输出：2
     * 示例 2：
     * 输入：N = 3, trust = [[1,3],[2,3]]
     * 输出：3
     */
    public int findJudge(int N, int[][] trust) {
        int[] degree = new int[N + 1];
        int judge = -1;
        for (int[] t : trust) {
            degree[t[1]]++;
            degree[t[0]]--;
        }
        for (int i = 1; i <= N; i++) {
            if (degree[i] == N - 1) {
                judge = i;
                break;
            }
        }
        return judge;
    }
}
