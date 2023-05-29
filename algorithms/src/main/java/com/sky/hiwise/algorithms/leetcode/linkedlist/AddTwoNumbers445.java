package com.sky.hiwise.algorithms.leetcode.linkedlist;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class AddTwoNumbers445 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode cur = head;

        ListNode r1 = reverse(l1);
        ListNode r2 = reverse(l2);
        int plus = 0;


        while (r1 != null || r2 != null) {
            int a = r1 == null ? 0 : r1.val;
            int b = r2 == null ? 0 : r2.val;
            int sum = a + b + plus;
            plus = sum / 10;

            ListNode node = new ListNode(sum % 10);
            cur.next = node;
            cur = cur.next;

            if (r1 != null) {
                r1 = r1.next;
            }
            if (r2 != null) {
                r2 = r2.next;
            }
        }

        if (plus > 0) {
            cur.next = new ListNode(plus);
        }

        ListNode reverse = reverse(head.next);

        return reverse;
    }

    public ListNode reverse(ListNode head) {
        //递归终止条件
        if (head.next == null || head == null) {
            return head;
        }
        ListNode node = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * 445. 两数相加 II
     * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。
     * 它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * 进阶:如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
     * 示例:  输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)  输出: 7 -> 8 -> 0 -> 7
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers445(ListNode l1, ListNode l2) {

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int bits = 0;
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            int sum = stack1.pop() + stack2.pop() + bits;
            bits = sum/10;
            stack.push(sum % 10);
        }

        while(!stack1.isEmpty()) {
            int sum = stack1.pop() + bits;
            bits = sum/10;
            stack.push(sum % 10);
        }

        while(!stack2.isEmpty()) {
            int sum = stack2.pop() + bits;
            bits = sum/10;
            stack.push(sum % 10);
        }

        // l1到达尾端，l2到达尾端,判断最末端是否有进位
        if(bits == 1){
            stack.push(1);
        }

        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead;
        while (!stack.isEmpty()) {
            dummyHead.next = new ListNode(stack.pop());
            dummyHead = dummyHead.next;
        }

        return  temp.next;
    }

    /**
     * 设立链表的虚拟头节点
     * 203. 删除链表中的节点
     * 删除链表中等于给定值 val 的所有节点。
     * 示例:  输入: 1->2->6->3->4->5->6, val = 6  输出: 1->2->3->4->5
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode curr = dummyHead;
        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 82. 删除排序链表中的重复元素 II
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     * 示例 1:  输入: 1->2->3->3->4->4->5  输出: 1->2->5
     * 示例 2:  输入: 1->1->1->2->3  输出: 2->3
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode curr = dummyHead.next;
        boolean isDelCurr = false;

        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
                isDelCurr = true;
            } else {
                if (isDelCurr) {
                    pre.next = curr.next;
                    curr = pre.next;   //curr = curr.next;
                    isDelCurr = false;
                } else {
                    pre = pre.next;
                    curr = curr.next;
                }
            }
        }

        if (isDelCurr) {
            pre.next = curr.next;
            curr = pre.next;   //curr = curr.next;
            isDelCurr = false;
        }

        return dummyHead.next;
    }

    public static ListNode deleteDuplicates1(ListNode head) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        ListNode curr = head;
        boolean isDelCurr = false;
        while (curr != null && curr.next != null) {
            if(curr.val == curr.next.val) {
                curr.next = curr.next.next;
                isDelCurr = true;
            } else {
                if (isDelCurr) {
                    pre.next = curr.next;
                    isDelCurr = false;
                } else {
                    pre = pre.next;
                }
                curr = curr.next;
            }
        }
        if (isDelCurr) {
            pre.next = curr.next;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,3,4,4);
        ListNode listNode = genListNode(list);
        ListNode node = deleteDuplicates1(listNode);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    private static ListNode genListNode(List<Integer> list) {
        ListNode dummyNode = new ListNode(-1);
        ListNode currNode = dummyNode;
        for (Integer item : list) {
            currNode.next = new ListNode(item);
            currNode = currNode.next;
        }
        return dummyNode.next;
    }


    /**
     * 21. 合并两个有序链表
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * 示例：  输入：1->2->4, 1->3->4  输出：1->1->2->3->4->4
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;

        while(l1 != null && l2 != null) {
            if (l1.val < l2.val ) {
                curr.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                curr.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            curr = curr.next;
        }
//        while(l1 != null) {
//            curr.next = new ListNode(l1.val);
//            l1 = l1.next;
//            curr = curr.next;
//        }
//
//        while (l2 != null) {
//            curr.next = new ListNode(l2.val);
//            l2 = l2.next;
//            curr = curr.next;
//        }

        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            curr.next = l2;
        } else {
            curr.next = l1;
        }
        return dummyHead.next;
    }


    /**
     * 面试题 02.05. 链表求和
     * 给定两个用链表表示的整数，每个节点包含一个数位。
     * 这些数位是反向存放的，也就是个位排在链表首部。
     * 编写函数对这两个整数求和，并用链表形式返回结果。
     * 示例：
     * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
     * 输出：2 -> 1 -> 9，即912
     * 进阶：假设这些数位是正向存放的，请再做一遍。
     * 示例：
     * 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
     * 输出：9 -> 1 -> 2，即912
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers0205(ListNode l1, ListNode l2) {
        int plus = 0;
        ListNode cur = new ListNode(-1);
        ListNode head = cur;
        while(l1 != null || l2 != null) {
            int a = (l1 != null) ? l1.val : 0;
            int b = (l2 != null) ? l2.val : 0;
            int sum = a + b + plus;
            plus = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (plus > 0) {
            cur.next = new ListNode(plus);
        }
        return head.next != null ? head.next : null;
    }

}
