package com.sky.hiwise.algorithms.leetcode.tree;

import com.sky.hiwise.algorithms.leetcode.linkedlist.ListNode;

public class SortedListToBST109 {


    /**
     * 109. 有序链表转换二叉搜索树
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * 示例:
     * 给定的有序链表： [-10, -3, 0, 5, 9],
     * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if(head.next == null){
            return new TreeNode(head.val);
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode preSlow = null;
        while (fast.next != null && fast.next.next != null) {
            preSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode mid = new TreeNode(slow.val);
        if (preSlow != null) {
            preSlow.next = null;
            mid.left = sortedListToBST(head);
        }
        if (slow.next != null) {
            mid.right = sortedListToBST(slow.next);
        }
        return mid;
    }
    /**
     * 方法 1：递归
     * 题目中最重要的要求是需要利用链表中的节点，构建一颗高度平衡的二叉搜索树，好消息是链表中的元素是升序的。
     * 众所周知，一棵二叉搜索树是一棵有根二叉树并且对于所有节点满足特殊的性质：
     * 对于树中任意一个点，它的权值必然 ≥ 所有左子树节点的权值，≤ 所有右子树节点的权值。因为二叉树具有递归的子结构，二叉搜索树也同理：所有子树也是二叉搜索树。
     * 当前方法和下一个方法的主要思路是：
     * 给定列表中的中间元素将会作为二叉搜索树的根，该点左侧的所有元素递归的去构造左子树，同理右侧的元素构造右子树。这必然能够保证最后构造出的二叉搜索树是平衡的。
     * 算法
     * 由于我们得到的是一个有序链表而不是数组，我们不能直接使用下标来访问元素。我们需要知道链表中的中间元素。
     * 我们可以利用两个指针来访问链表中的中间元素。假设我们有两个指针 slow_ptr 和 fast_ptr。slow_ptr 每次向后移动一个节点而 fast_ptr 每次移动两个节点。
     * 当 fast_ptr 到链表的末尾时 slow_ptr 就访问到链表的中间元素。对于一个偶数长度的数组，中间两个元素都可用来作二叉搜索树的根。
     * 当找到链表中的中间元素后，我们将链表从中间元素的左侧断开，做法是使用一个 prev_ptr 的指针记录 slow_ptr 之前的元素，也就是满足 prev_ptr.next = slow_ptr。断开左侧部分就是让 prev_ptr.next = None。
     * 我们只需要将链表的头指针传递给转换函数，进行高度平衡二叉搜索树的转换。所以递归调用的时候，左半部分我们传递原始的头指针；右半部分传递 slow_ptr.next 作为头指针。
     * 通过样例来理解算法的具体过程。
     */

}
