package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxLevelSumBTree1161 {
    /**
     * 1161. 最大层内元素和
     * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
     * 请你找出层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
     * 示例 1：
     * 输入：root = [1,7,0,7,-8,null,null]
     * 输出：2
     * 解释：
     * 第 1 层各元素之和为 1，
     * 第 2 层各元素之和为 7 + 0 = 7，
     * 第 3 层各元素之和为 7 + -8 = -1，
     * 所以我们返回第 2 层的层号，它的层内元素之和最大。
     * 示例 2：
     * 输入：root = [989,null,10250,98693,-89388,null,null,null,-32127]
     * 输出：2
     */
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return -1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;
        int maxSum = Integer.MIN_VALUE, ans = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int temp = 0;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                temp += curr.val;
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            if (temp > maxSum) {
                maxSum = temp;
                ans = level;
            }
            level++;
        }
        return ans;
    }
}
