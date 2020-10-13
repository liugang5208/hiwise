package com.sky.hiwise.algorithms.leetcode.interview;

import com.sky.hiwise.algorithms.leetcode.tree.TreeNode;

public class IsValidBST0405 {

    /**
     * 面试题 04.05. 合法二叉搜索树
     * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
     * 示例 1:
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     * 示例 2:
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;
        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;
        if (! helper(node.right, val, upper)) return false;
        if (! helper(node.left, lower, val)) return false;
        return true;
    }
}
