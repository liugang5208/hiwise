package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class FindBottomLeftValue513 {
    /**
     * 513. 找树左下角的值
     * 给定一个二叉树，在树的最后一行找到最左边的值。
     * 示例 1:
     * 输入:
     *
     *     2
     *    / \
     *   1   3
     * 输出:
     * 1
     * 示例 2:
     * 输入:
     *         1
     *        / \
     *       2   3
     *      /   / \
     *     4   5   6
     *        /
     *       7
     * 输出:
     * 7
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int ans = root.val;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                ans = curr.val;
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                if (curr.left != null) {
                    queue.add(curr.left);
                }
            }
        }
        return ans;
    }
}
