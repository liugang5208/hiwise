package com.sky.hiwise.algorithms.leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicateNode0201 {

    /**
     * 面试题 02.01. 移除重复节点
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     * 示例1:
     *  输入：[1, 2, 3, 3, 2, 1]
     *  输出：[1, 2, 3]
     * 示例2:
     *  输入：[1, 1, 1, 1, 2]
     *  输出：[1, 2]
     * 提示：
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode curr = head;
        Set<Integer> set = new HashSet<>();
        while (curr != null && curr.next != null) {
            set.add(curr.val);
            ListNode next = curr.next;
            while (next != null && set.contains(next.val)) {
                next = next.next;
            }
            curr.next = next;
            curr = curr.next;
        }
        return head;
    }
}
