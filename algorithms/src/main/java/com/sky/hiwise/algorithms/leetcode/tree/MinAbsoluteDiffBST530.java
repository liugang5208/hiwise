package com.sky.hiwise.algorithms.leetcode.tree;

public class MinAbsoluteDiffBST530 {

    /**
     * 530. 二叉搜索树的最小绝对差
     * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
     * 示例：
     * 输入：
     *
     *    1
     *     \
     *      3
     *     /
     *    2
     * 输出：
     * 1
     * 解释：
     * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
     */
    public TreeNode pre = null;
    public int ans = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return ans;
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (pre != null) {
            ans = Math.min(ans, Math.abs(root.val - pre.val));
        }
        pre = root;
        inorder(root.right);
    }
}
