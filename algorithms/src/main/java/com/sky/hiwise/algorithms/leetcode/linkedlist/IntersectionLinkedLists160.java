package com.sky.hiwise.algorithms.leetcode.linkedlist;

public class IntersectionLinkedLists160 {

    /**
     * 160. 相交链表
     * 编写一个程序，找到两个单链表相交的起始节点。
     *
     * 如下面的两个链表：
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode headA1 = headA, headB1 = headB;
        while (headA1 != headB1) {
            headA1 = headA1 == null ? headB : headA1.next;
            headB1 = headB1 == null ? headA : headB1.next;
        }

        return headA1;
    }

    public static void main(String[] args) {
        int a = 64;
        System.out.println(a & 4);
    }
}
