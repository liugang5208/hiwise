package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.*;

public class MinJumpsReachHome1654 {
    /**
     * 1654. 到家的最少跳跃次数
     * 有一只跳蚤的家在数轴上的位置 x 处。请你帮助它从位置 0 出发，到达它的家。
     * 跳蚤跳跃的规则如下：
     * 它可以 往前 跳恰好 a 个位置（即往右跳）。
     * 它可以 往后 跳恰好 b 个位置（即往左跳）。
     * 它不能 连续 往后跳 2 次。
     * 它不能跳到任何 forbidden 数组中的位置。
     * 跳蚤可以往前跳 超过 它的家的位置，但是它 不能跳到负整数 的位置。
     * 给你一个整数数组 forbidden ，其中 forbidden[i] 是跳蚤不能跳到的位置，同时给你整数 a， b 和 x ，请你返回跳蚤到家的最少跳跃次数。如果没有恰好到达 x 的可行方案，请你返回 -1 。
     * 示例 1：
     * 输入：forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
     * 输出：3
     * 解释：往前跳 3 次（0 -> 3 -> 6 -> 9），跳蚤就到家了。
     * 提示：
     *
     * 1 <= forbidden.length <= 1000
     * 1 <= a, b, forbidden[i] <= 2000
     * 0 <= x <= 2000
     * forbidden 中所有位置互不相同。
     * 位置 x 不在 forbidden 中。
     */
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> forbidSet = new HashSet<>();
        for (int forbid : forbidden) {
            forbidSet.add(forbid);
        }

        int steps = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0));
        Set<Integer> visitedA = new HashSet<>();
        Set<Integer> visitedB = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                System.out.println("step:" + steps + ", curr:" + curr.val);
                if (curr.val == x) {
                    return steps;
                }
                int nextA = curr.val + a;
                if (nextA < 6000 && nextA > 0 && !forbidSet.contains(nextA) && !visitedA.contains(nextA)) {
                    queue.add(new Node(nextA, 0));
                    visitedA.add(nextA);
                }
                int nextB = curr.val - b;
                if (curr.count != 1 && nextB > 0 && !forbidSet.contains(nextB) && !visitedB.contains(nextB)) {
                    queue.add(new Node(nextB, 1));
                    visitedB.add(nextB);
                }
            }
            steps++;
        }
        return -1;
    }

    class Node {
        public int val;
        public int count;
        public Node(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        int[] forbidden = new int[]{8,3,16,6,12,20};
        int a = 15, b = 13, x = 11;
        System.out.println((new MinJumpsReachHome1654()).minimumJumps(forbidden, a, b, x));

    }
}
