package com.sky.hiwise.algorithms.leetcode.linkedlist;

public class FlattenMultiLevelLinkedList430 {
    /**
     * 430. 扁平化多级双向链表
     * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
     * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
     * 示例 1：
     * 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     * 输出：[1,2,3,7,8,11,12,9,10,4,5,6]
     * 解释：
     */

    public Node flatten(Node head) {
       dfs(head);
       return head;
    }

    public Node dfs(Node node) {
        Node cur = node;
        Node last = null;
        while (cur != null) {
            Node next = cur.next;
            if (cur.child != null) {
                Node childLast = dfs(cur.child);
                next = cur.next;
                cur.next = cur.child;
                cur.child.prev = cur;
                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }
                cur.child = null;
                last = childLast;
            } else {
                last = cur;
            }
            cur = next;
        }
        return last;
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };
}
