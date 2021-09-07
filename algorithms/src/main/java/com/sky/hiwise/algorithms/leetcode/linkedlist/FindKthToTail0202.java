package com.sky.hiwise.algorithms.leetcode.linkedlist;

public class FindKthToTail0202 {
    /**
     * 面试题 02.02. 返回倒数第 k 个节点
     * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
     *
     * 注意：本题相对原题稍作改动
     *
     * 示例：
     *
     * 输入： 1->2->3->4->5 和 k = 2
     * 输出： 4
     * 说明：
     *
     * 给定的 k 保证是有效的。
     */
    public int kthToLast(ListNode head, int k) {
        if (head == null || k <= 0) {
            return -1;
        }
        ListNode fast = head,slow = head;
        int count = 0, index = k;
        while(fast!= null) {
            count ++;
            fast = fast.next;
            if(k < 1 && slow != null) {
                slow = slow.next;
            }
            k--;
        }
        if (count < index) {
            return -1;
        }
        return slow.val;
    }

    /**
     *
     题目描述
     剑指offer: 输入一个链表，输出该链表中倒数第k个结点。
     问题分析
     链表中倒数第k个节点也就是正数第(L-K+1)个节点，知道了只一点，这一题基本就没问题！
     首先两个节点/指针，一个节点 node1 先开始跑，指针 node1 跑到 k-1 个节点后，
     另一个节点 node2 开始跑，当 node1 跑到最后时，
     node2 所指的节点就是倒数第k个节点也就是正数第(L-K+1)个节点。
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        // 如果链表为空或者k小于等于0
        if (head == null || k <= 0) {
            return null;
        }
        // 声明两个指向头结点的节点
        ListNode node1 = head, node2 = head;
        // 记录节点的个数
        int count = 0;
        // 记录k值，后面要使用
        int index = k;

        // p指针先跑，并且记录节点数，当node1节点跑了k-1个节点后，node2节点开始跑，
        // 当node1节点跑到最后时，node2节点所指的节点就是倒数第k个节点
        while (node1 != null) {
            node1 = node1.next;
            count++;
            if (k < 1 && node2 != null) {
                node2 = node2.next;
            }
            k --;
        }

        // 如果节点个数小于所求的倒数第k个节点，则返回空
        if (count < index)
            return null;
        return node2;
    }

    //面试题22. 链表中倒数第k个节点
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode fast = head,slow = head;
        int count = 0, index = k;
        while(fast!= null) {
            count ++;
            fast = fast.next;
            if(k < 1 && slow != null) {
                slow = slow.next;
            }
            k--;
        }
        if (count < index) {
            return null;
        }
        return slow;
    }
}
