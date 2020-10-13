package com.sky.hiwise.algorithms.leetcode.linkedlist;

public class PalindromeList234 {

    /**
     * 234. 回文链表
     * 请检查一个链表是否为回文链表。
     * 进阶：你能在 O(n) 的时间和 O(1) 的额外空间中做到吗？
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow = head, fast = head.next, pre = null, prepre = null;
        while(fast != null && fast.next != null) {
            //反转前半段链表
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            //先移动指针再来反转
            pre.next = prepre;
            prepre = pre;
        }
        //slow 的next为后半段开始
        ListNode p2 = slow.next;
        //slow 链接在前半段最后
        slow.next = pre;
        //链表奇偶 fast为空则以slow为中心
        ListNode p1 = fast == null ? slow.next : slow;
        while(p1 != null) {
            if(p1.val != p2.val)
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
    /**
     * 思想很很简单，用2个指针，一个low，一个fast，fast是low的2倍，所以可以达到2分链表的效果
     * ，在移动指针时同时对前半部分链表进行反转。最后直接比较被分开的2个链表
     * 因为不能改变当前slow的next，不然就无法跳到下一个元素，所以这里用pre和prepre实现指针的反转
     * 时间复杂度：第一个循环O(n/2)，第2个循环O(n/2)
     */
    /**
     * 算法2：
     * 我们可以分为两个步骤：
     * 复制链表值到数组列表中。
     * 使用双指针法判断是否为回文。
     */
}
