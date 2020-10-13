package com.sky.hiwise.algorithms.leetcode.greedy;

import java.util.Arrays;

public class MostProfitAssignWork826 {

    public static void main(String[] args) {
        int[] difficulty = new int[]{2,4,6,8,10};
        int[] profit = new int[]{10,20,30,40,50};
        int[] worker = new int[]{4,5,6,7};
        System.out.println((new MostProfitAssignWork826()).maxProfitAssignment(difficulty,profit,worker));
    }
    /**
     * 826. 安排工作以达到最大收益
     * 有一些工作：difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。
     *
     * 现在我们有一些工人。worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
     *
     * 每一个工人都最多只能安排一个工作，但是一个工作可以完成多次。
     *
     * 举个例子，如果 3 个工人都尝试完成一份报酬为 1 的同样工作，那么总收益为 $3。如果一个工人不能完成任何工作，他的收益为 $0 。
     *
     * 我们能得到的最大收益是多少？
     * 输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
     * 输出: 100
     * 解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/most-profit-assigning-work
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param difficulty
     * @param profit
     * @param worker
     * @return
     */
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        Point[] jobs = new Point[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Point(difficulty[i], profit[i]);
        }
        Arrays.sort(jobs);
        Arrays.sort(worker);
        int ans = 0, best = 0, idx = 0;
        for (int skill : worker) {
            while (idx < n && skill >= jobs[idx].index) {
                best = Math.max(best, jobs[idx++].value);
            }
            ans += best;
        }
        return ans;
    }

    class Point implements Comparable<Point> {
        private int index;
        private int value;

        public Point(int idx, int val) {
            this.index = idx;
            this.value = val;
        }

        @Override
        public int compareTo(Point o) {
            return this.index - o.index;
        }
    }
}
