package com.sky.hiwise.algorithms.leetcode.tree;

/**
 * @date: 2023-05-24 15:45
 **/
public class InsufficientNodes1080 {

    /**
     * 1080. 根到叶路径上的不足节点
     * 给你二叉树的根节点 root 和一个整数 limit ，请你同时删除树中所有 不足节点 ，并返回最终二叉树的根节点。
     * 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为 不足节点 ，需要被删除。
     * 叶子节点，就是没有子节点的节点。
     */
    public int limit;
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        this.limit = limit;
        boolean res = check(root, 0);
        return res ? root : null;
    }

    public boolean check(TreeNode node, int sum) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
           return sum + node.val >= limit;
        }

        boolean left = check(node.left, sum + node.val);
        boolean right = check(node.right, sum + node.val);
        if (!left) {
            node.left = null;
        }
        if (!right) {
            node.right = null;
        }
        return left || right;
    }
}
