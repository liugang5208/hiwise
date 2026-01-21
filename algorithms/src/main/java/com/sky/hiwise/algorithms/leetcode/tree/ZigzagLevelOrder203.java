package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.*;

public class ZigzagLevelOrder203 {

    /**
     * 103. 二叉树的锯齿形层次遍历
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回锯齿形层次遍历如下：
     *
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (true) {
            List<Integer> temp = new ArrayList<>();
            while (!stack1.isEmpty()) {
                TreeNode current = stack1.pop();
                temp.add(current.val);
                if (current.left != null) {
                    stack2.add(current.left);
                }
                if (current.right != null) {
                    stack2.add(current.right);
                }
            }
            if (!temp.isEmpty()) {
                res.add(temp);
            } else break;
            List<Integer> temp1 = new ArrayList<>();
            while (!stack2.isEmpty()) {
                TreeNode current = stack2.pop();
                temp1.add(current.val);
                if (current.right != null) {
                    stack1.add(current.right);
                }
                if (current.left != null) {
                    stack1.add(current.left);
                }
            }
            if (!temp1.isEmpty()) {
                res.add(temp1);
            } else break;
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = queue.size();
        boolean flag = true;//true:奇数层；false:偶数层
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            if (flag) {
                TreeNode currNode = queue.pollFirst();
                list.add(currNode.val);
                if (currNode.left != null) {
                    queue.addLast(currNode.left);
                }
                if (currNode.right != null) {
                    queue.addLast(currNode.right);
                }
            } else {
                TreeNode currNode = queue.pollLast();
                list.add(currNode.val);
                if (currNode.right != null) {
                    queue.addFirst(currNode.right);
                }
                if (currNode.left != null) {
                    queue.addFirst(currNode.left);
                }
            }
            count --;
            if (count == 0) {
                count = queue.size();
                flag = !flag;
                res.add(new ArrayList<>(list));
                list.clear();
            }
        }
        return res;
    }
}
