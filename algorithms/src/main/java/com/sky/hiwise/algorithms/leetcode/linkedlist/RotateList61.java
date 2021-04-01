package com.sky.hiwise.algorithms.leetcode.linkedlist;

public class RotateList61 {
    /**
     * 61. 旋转链表
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     * 示例 1：
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[4,5,1,2,3]
     * 示例 2：
     * 输入：head = [0,1,2], k = 4
     * 输出：[2,0,1]
     * 提示：
     * 链表中节点的数目在范围 [0, 500] 内
     * -100 <= Node.val <= 100
     * 0 <= k <= 2 * 109
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        curr.next = head;
        while (add > 0) {
            curr = curr.next;
            add--;
        }
        ListNode ret = curr.next;
        curr.next = null;
        return  ret;
    }
    /**
     * 方法一：闭合为环
     * 思路及算法
     * 记给定链表的长度为 nn，注意到当向右移动的次数 k \geq nk≥n 时，我们仅需要向右移动 k \bmod nkmodn 次即可。因为每 nn 次移动都会让链表变为原状。这样我们可以知道，新链表的最后一个节点为原链表的第 (n - 1) - (k \bmod n)(n−1)−(kmodn) 个节点（从 00 开始计数）。
     * 这样，我们可以先将给定的链表连接成环，然后将指定位置断开。
     * 具体代码中，我们首先计算出链表的长度 nn，并找到该链表的末尾节点，将其与头节点相连。这样就得到了闭合为环的链表。然后我们找到新链表的最后一个节点（即原链表的第 (n - 1) - (k \bmod n)(n−1)−(kmodn) 个节点），将当前闭合为环的链表断开，即可得到我们所需要的结果。
     * 特别地，当链表长度不大于 11，或者 kk 为 nn 的倍数时，新链表将与原链表相同，我们无需进行任何处理
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/rotate-list/solution/xuan-zhuan-lian-biao-by-leetcode-solutio-woq1/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
