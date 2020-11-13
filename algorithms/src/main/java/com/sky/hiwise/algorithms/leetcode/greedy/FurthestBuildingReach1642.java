package com.sky.hiwise.algorithms.leetcode.greedy;

import java.util.PriorityQueue;

public class FurthestBuildingReach1642 {

    /**
     * 1642. 可以到达的最远建筑
     * 给你一个整数数组 heights ，表示建筑物的高度。另有一些砖块 bricks 和梯子 ladders 。
     *
     * 你从建筑物 0 开始旅程，不断向后面的建筑物移动，期间可能会用到砖块或梯子。
     *
     * 当从建筑物 i 移动到建筑物 i+1（下标 从 0 开始 ）时：
     *
     * 如果当前建筑物的高度 大于或等于 下一建筑物的高度，则不需要梯子或砖块
     * 如果当前建筑的高度 小于 下一个建筑的高度，您可以使用 一架梯子 或 (h[i+1] - h[i]) 个砖块
     * 如果以最佳方式使用给定的梯子和砖块，返回你可以到达的最远建筑物的下标（下标 从 0 开始 ）。
     * 输入：heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
     * 输出：4
     * 解释：从建筑物 0 出发，你可以按此方案完成旅程：
     * - 不使用砖块或梯子到达建筑物 1 ，因为 4 >= 2
     * - 使用 5 个砖块到达建筑物 2 。你必须使用砖块或梯子，因为 2 < 7
     * - 不使用砖块或梯子到达建筑物 3 ，因为 7 >= 6
     * - 使用唯一的梯子到达建筑物 4 。你必须使用砖块或梯子，因为 6 < 9
     * 无法越过建筑物 4 ，因为没有更多砖块或梯子。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/furthest-building-you-can-reach
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int sumH = 0;
        for (int i = 1; i < heights.length; i++) {
            int gapH = heights[i] - heights[i - 1];
            if (gapH > 0) {
                pq.add(gapH);
                if (pq.size() > ladders) {
                    sumH += pq.poll();
                }
                if (sumH > bricks) {
                    return i - 1;
                }
            }
        }
        return heights.length - 1;
    }
    /**
     * 方法一：优先队列 + 贪心
     * 思路与算法
     * 在移动的过程中，我们会需要若干次需要使用砖块或者梯子的情况。假设当前我们需要移动到下一建筑物，但必须使用 11 架梯子或者 \Delta hΔh 个砖块，那么我们如何抉择是使用梯子还是砖块呢？
     * 我们可以用贪心的思路来想这个问题。「梯子」相当于一次性的无限量砖块，那么我们一定是把梯子用在刀刃上。也就是说，如果我们有 ll 架梯子，那么我们会在 \Delta hΔh 最大的那 ll 次使用梯子，而在剩余的情况下使用砖块。
     * 这样一来，我们就可以得到正确的算法了：我们使用优先队列实时维护不超过 ll 个最大的 \Delta hΔh，这些就是使用梯子的地方。对于剩余的 \Delta hΔh，我们需要使用砖块，因此需要对它们进行累加，如果某一时刻这个累加值超过了砖块的数目 bb，那么我们就再也无法移动了。
     * 作者：zerotrac2
     * 链接：https://leetcode-cn.com/problems/furthest-building-you-can-reach/solution/ke-yi-dao-da-de-zui-yuan-jian-zhu-by-zerotrac2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
