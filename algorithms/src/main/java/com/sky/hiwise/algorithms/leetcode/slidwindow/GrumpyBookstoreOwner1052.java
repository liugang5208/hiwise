package com.sky.hiwise.algorithms.leetcode.slidwindow;

public class GrumpyBookstoreOwner1052 {
    /**
     * 1052. 爱生气的书店老板
     * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
     * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
     * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
     * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
     * 示例：
     * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
     * 输出：16
     * 解释：
     * 书店老板在最后 3 分钟保持冷静。
     * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
     * 提示：
     * 1 <= X <= customers.length == grumpy.length <= 20000
     * 0 <= customers[i] <= 1000
     * 0 <= grumpy[i] <= 1
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int total = 0, n = customers.length;
        for (int i = 0; i < n; i++) {
            total += grumpy[i] == 0 ? customers[i] : 0;
        }

        int incr = 0;
        for (int i = 0; i < X; i++) {
            incr += grumpy[i] * customers[i];
        }
        int maxIncr = incr;
        for (int i = X; i < n; i++) {
            incr = incr - grumpy[i - X] * customers[i - X] + grumpy[i] * customers[i];
            maxIncr = Math.max(maxIncr, incr);
        }
        return total + maxIncr;
    }
    /**
     * 上述过程可以看成维护一个长度为 XX 的滑动窗口。当滑动窗口从下标范围 [i-X,i-1][i−X,i−1] 移动到下标范围 [i-X+1,i][i−X+1,i] 时，下标 i-Xi−X 从窗口中移出，下标 ii 进入到窗口内。
     * 利用上述关系，可以在 O(1)O(1) 的时间内通过 \textit{increase}_{i-1}increase
     * i−1
     *   得到 \textit{increase}_iincrease
     * 由于长度为 XX 的子数组的最小结束下标是 X-1X−1，因此第一个长度为 XX 的子数组对应的 \textit{increase}increase 的值为 \textit{increase}_{X-1}increase
     * X−1
     *  ，需要通过遍历数组 \textit{customers}customers 和 \textit{grumpy}grumpy 的前 XX 个元素计算得到。从 \textit{increase}_Xincrease
     * X
     *   到 \textit{increase}_{n-1}increase
     * n−1
     *   的值则可利用上述关系快速计算得到。只需要遍历数组 \textit{customers}customers 和 \textit{grumpy}grumpy 各一次即可得到 X \le i<nX≤i<n 的每个 \textit{increase}_iincrease
     * i
     *   的值，时间复杂度是 O(n)O(n)。
     * 又由于计算初始的 \textit{total}total 的值需要遍历数组 \textit{customers}customers 和 \textit{grumpy}grumpy 各一次，因此整个过程只需要遍历数组 \textit{customers}customers 和 \textit{grumpy}grumpy 各两次，时间复杂度是 O(n)O(n)。
     * 在上述过程中维护增加的满意顾客的最大数量，记为 \textit{maxIncrease}maxIncrease，则满意顾客的最大总数是 \textit{total}+\textit{maxIncrease}total+maxIncrease。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/grumpy-bookstore-owner/solution/ai-sheng-qi-de-shu-dian-lao-ban-by-leetc-dloq/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
