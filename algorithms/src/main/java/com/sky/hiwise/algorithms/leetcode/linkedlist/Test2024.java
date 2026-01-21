package com.sky.hiwise.algorithms.leetcode.linkedlist;

public class Test2024 {


    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode dummy =new ListNode(0);
        dummy.next=head;
        ListNode cur=dummy;
        while(cur.next!=null&&cur.next.next!=null){
            if(cur.next.val==cur.next.next.val){
                int i=cur.next.val;
                while(cur.next!=null&&cur.next.val==i){
                    cur.next=cur.next.next;
                }
            }
            else{
                cur=cur.next;
            }
        }
        return dummy.next;
    }

    static class ListNode {

        public int val;

        public ListNode next;

        public ListNode(int x) { val = x; }

    }
    public static void main(String[] args) {
        int[] test=new int[]{1,1,1,2,3};
        ListNode dummy= new  ListNode(0);
        ListNode cur=dummy;
        for(int i=0;i<5;i++){
            ListNode p=new ListNode(test[i]);
            cur.next=p;
            cur=cur.next;
        }
        ListNode res=deleteDuplicates(dummy.next);
        ListNode a=res;
        while(a!=null){
            System.out.println(a.val);
            a=a.next;
        }
    }
}
