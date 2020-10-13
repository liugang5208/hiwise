package com.sky.hiwise.algorithms.leetcode.string;


import com.sky.hiwise.algorithms.leetcode.linkedlist.ListNode;
import com.sky.hiwise.algorithms.leetcode.tree.TreeNode;

public class SortedListToBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static TreeNode sortedListToBST(ListNode head) {
        int len = 0;
        ListNode tmp = head;
        while(tmp != null){
            len ++;
            tmp = tmp.next;
        }
        ListNodeWrapper headWrapper = new ListNodeWrapper();
        headWrapper.node = head;
        return sortedListToBST(headWrapper, 0, len-1);
    }
		 
    public static TreeNode sortedListToBST(ListNodeWrapper headWrapper, int start, int end){
        if(start > end) return null;
        int mid = start + (end - start)/2;
        TreeNode left    = sortedListToBST(headWrapper, start, mid - 1);
        TreeNode parent  = new TreeNode(headWrapper.node.val);
        parent.left      = left;
        headWrapper.node = headWrapper.node.next;
        parent.right     = sortedListToBST(headWrapper, mid + 1, end);
        return parent;
    }
		 
    static class ListNodeWrapper {
        public ListNode node;
    }

		
	public static TreeNode sortedListToBST2(ListNode head) {
		if (head == null) {
			return null;
		}
		
		if(head.next == null){
            return new TreeNode(head.val);
        }
		
		//用快慢指针找到中间节点
        ListNode slow = head;
        ListNode fast = head;
        ListNode preSlow = null;
        
        while (fast.next != null && fast.next.next != null) {
        	preSlow = slow;
            slow    = slow.next;
            fast    = fast.next.next;
        }
        
        //分别递归左右两部分
        TreeNode mid = new TreeNode(slow.val);
        if (preSlow != null) {
        	preSlow.next = null;
        	mid.left = sortedListToBST2(head);
        }
        
        if(slow.next != null){
            mid.right = sortedListToBST2(slow.next);
        }
        return mid;        
	}
}
