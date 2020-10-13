package com.sky.hiwise.algorithms.leetcode.linkedlist;

public class ReorderList143 {

    /**
     * 143. 重排链表
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 示例 1:
     *
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     * 示例 2:
     *
     * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     * @param head
     */
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }
        ListNode p1 = head, p2=head;
        while(p2.next!=null && p2.next.next!=null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        p2 = p1.next;
        p1.next = null;
        p1 = head;

        ListNode pre = null;
        while (p2.next !=null) {
            ListNode next = p2.next;
            p2.next = pre;
            pre = p2;
            p2 = next;
        }
        p2.next = pre;

        ListNode next1,next2;
        while(p2!=null) {
            next1 = p1.next;
            next2 = p2.next;
            p1.next = p2;
            p2.next = next1;
            p1 = next1;
            p2 = next2;
        }
    }
    /**
     * 可以把本题分为3个步骤：
     * 使用双指针将链表分成两段
     * 将链表后半段进行翻转
     * 将两段链表进行合并
     * 另外有一些命名的规则，可以使代码更为清晰：
     * p为遍历节点，如果有两个p1在前，p2在后
     * next为遍历节点p的下一个节点，next1则为p1的下一个节点
     * 如果有必要存head值，head2为第二段链表的规则
     */

    /**
     * 143. 重排链表
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 示例 1:
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     * 示例 2:
     * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     */
    public void reorderList1(ListNode head) {
        if (head == null || head.next == null) return;

        ListNode p1 = head;
        ListNode p2 = head;

        // 找到链表的一半
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        // 将链表分为两段
        p2 = p1.next;
        p1.next = null;
        p1 = head;

        // 将后半段进行链表的翻转
        ListNode head2 = p2;
        ListNode next2;
        while (p2.next != null) {
            next2 = p2.next;
            p2.next = next2.next;
            next2.next = head2;
            head2 = next2;
        }
        p2 = head2;

        // 两条链表进行合并
        ListNode next1;
        while (p2 != null) {
            next1 = p1.next;
            next2 = p2.next;

            p1.next = p2;
            p2.next = next1;

            p1 = next1;
            p2 = next2;
        }
    }
    /**
     * 可以把本题分为3个步骤：
     * 使用双指针将链表分成两段
     * 将链表后半段进行翻转
     * 将两段链表进行合并
     * 另外有一些命名的规则，可以使代码更为清晰：
     * p为遍历节点，如果有两个p1在前，p2在后
     * next为遍历节点p的下一个节点，next1则为p1的下一个节点
     * 如果有必要存head值，head2为第二段链表的规则
     */
}
