package com.sky.hiwise.algorithms.leetcode.linkedlist;

public class Test {

    public static void main(String[] args) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        int[] record = {1,1,1,2,3};
        for(int r : record){
            p.next = new ListNode(r);
            p=p.next;
        }
        ListNode node = deleteDuplicates(dummy.next);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode p = head;
        while(p != null){
            ListNode p0 = pre;
            while(p.next != null && p.val == p.next.val){
                p = p.next;
                pre = pre.next;
            }
            p0.next = p;
            p = p.next;
            pre = pre.next;
        }
        return dummy.next;
    }
}
