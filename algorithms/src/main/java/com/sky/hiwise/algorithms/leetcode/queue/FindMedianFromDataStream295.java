package com.sky.hiwise.algorithms.leetcode.queue;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream295 {
    /**
     * 295. 数据流的中位数
     * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
     * 例如，
     * [2,3,4] 的中位数是 3
     * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
     * 设计一个支持以下两种操作的数据结构：
     * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
     * double findMedian() - 返回目前所有元素的中位数。
     * 示例：
     * addNum(1)
     * addNum(2)
     * findMedian() -> 1.5
     * addNum(3)
     * findMedian() -> 2
     * 进阶:
     * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
     * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
     */
    PriorityQueue<Integer> lo;
    PriorityQueue<Integer> hi;
    public FindMedianFromDataStream295() {
        lo = new PriorityQueue<>();  //存储较小一半数字的最大堆 从大到小排
        hi = new PriorityQueue<>(Collections.reverseOrder());  //用于存储较大一半数字的最小堆 从小到大排列
    }

    public void addNum(int num) {
        lo.offer(num);  //先入lo 从大到小排列完之后
        hi.offer(lo.poll());  //把堆顶元素（即最大值）转到hi
        if (lo.size() < hi.size()) {  //根据lo hi容量再均衡
            lo.offer(hi.poll());
        }
    }

    public double findMedian() {
        return lo.size() > hi.size() ? lo.peek() : (lo.peek() + hi.peek()) * 0.5;
    }

    /**
     * 算法：
     *
     * 两个优先级队列：
     * 用于存储较小一半数字的最大堆 lo
     * 用于存储较大一半数字的最小堆 hi
     * 最大堆 lo 允许存储的元素最多比最小堆 hi 多一个。因此，如果我们处理了 kk 元素：
     * 如果 k=2*n+1 \quad(\forall,n \in \mathbb z)k=2∗n+1(∀,n∈z) 则允许 lo 持有 n+1n+1 元素，而 hi 可以持有 nn 元素。
     * 如果 k=2*n\quad(\forall,n\in\mathbb z)k=2∗n(∀,n∈z)，那么两个堆都是平衡的，并且每个堆都包含 nn 个元素。
     * 这给了我们一个很好的特性，即当堆完全平衡时，中间值可以从两个堆的顶部派生。否则，最大堆 lo 的顶部保留合法的中间值。
     * 添加一个数 num：
     * 将 num 添加到最大堆 lo。因为 lo 收到了一个新元素，所以我们必须为 hi 做一个平衡步骤。因此，从 lo 中移除最大的元素并将其提供给 hi。
     * 在上一个操作之后，最小堆 hi 可能会比最大堆 lo 保留更多的元素。我们通过从 hi 中去掉最小的元素并将其提供给 lo 来解决这个问题。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream/solution/shu-ju-liu-de-zhong-wei-shu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
