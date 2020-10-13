package com.sky.hiwise.algorithms.leetcode.queue;

import com.sky.hiwise.algorithms.leetcode.linkedlist.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKLists23 {
    /**
     * 23. 合并K个排序链表
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     * 示例:
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     * 有k个有序数组，将他们归并为一个有序数组
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(len, Comparator.comparingInt(a -> a.val));
        ListNode dummyNode = new ListNode(-1);
        ListNode curNode = dummyNode;
        for (ListNode list : lists) {
            if (list != null) {
                // 这一步很关键，不能也没有必要将空对象添加到优先队列中
                priorityQueue.add(list);
            }
        }
        while (!priorityQueue.isEmpty()) {
            // 优先队列非空才能出队TaskService.java
            ListNode node = priorityQueue.poll();
            // 当前节点的 next 指针指向出队元素
            curNode.next = node;
            // 当前指针向前移动一个元素，指向了刚刚出队的那个元素
            curNode = curNode.next;
            if (curNode.next != null) {
                // 只有非空节点才能加入到优先队列中
                priorityQueue.add(curNode.next);
            }
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        Integer[] nums1 = {1, 4, 5};
        Integer[] nums2 = {1, 3, 4};
        Integer[] nums3 = {2, 6};
        ListNode head1 = new ListNode(nums1);
        ListNode head2 = new ListNode(nums2);
        ListNode head3 = new ListNode(nums3);
        ListNode[] lists = new ListNode[3];
        lists[0] = head1;
        lists[1] = head2;
        lists[2] = head3;
        MergeKLists23 solution = new MergeKLists23();
        //ListNode mergeKLists = solution.mergeKLists(lists);
        //System.out.println(mergeKLists);
        ListNode two = mergeTwoLists(head1, head3);
        System.out.println(two);
    }

    //分治算法
    public static ListNode mergeKLists1(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }
        return mergeK(lists, 0, len - 1);
    }

    public static ListNode mergeK(ListNode[] lists, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            ListNode leftNode = mergeK(lists, left, mid);
            ListNode rightNode = mergeK(lists, mid + 1, right);
            mergeTwoLists(leftNode, rightNode);
        } else if (left == right) {
            return lists[left];
        }
        return null;
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode currNode = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                currNode.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                currNode.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            currNode = currNode.next;
        }
        if (l1 != null) {
            currNode.next = l1;
        } else if (l2 != null) {
            currNode.next = l2;
        }
        return dummyHead.next;
    }

}
