package com.sky.hiwise.algorithms.leetcode.tree;

public class BTreeMaxiPathSum124 {

    /**
     * 124. 二叉树中的最大路径和
     * 给定一个非空二叉树，返回其最大路径和。
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。
     * 该路径至少包含一个节点，且不一定经过根节点。
     * 示例 1:
     * 输入: [1,2,3]
     *        1
     *       / \
     *      2   3
     * 输出: 6
     * 示例 2:
     * 输入: [-10,9,20,null,null,15,7]
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 输出: 42
     */
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return ans;
    }

    public int maxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = Math.max(maxSum(root.left), 0);
        int rightMax = Math.max(maxSum(root.right), 0);
        int pathSum = root.val + leftMax + rightMax;
        ans = Math.max(ans, pathSum);
        return root.val + Math.max(leftMax, rightMax);
    }
    /**
     * 算法
     * 初始化 max_sum 为最小可能的整数并调用函数 max_gain(node = root)。
     * 实现 max_gain(node) 检查是继续旧路径还是开始新路径：
     * 边界情况：如果节点为空，那么最大权值是 0 。
     * 对该节点的所有孩子递归调用 max_gain，计算从左右子树的最大权值：
     * left_gain = max(max_gain(node.left), 0) 和 right_gain = max(max_gain(node.right), 0)。
     * 检查是维护旧路径还是创建新路径。创建新路径的权值是：
     * price_newpath = node.val + left_gain + right_gain，当新路径更好的时候更新 max_sum。
     * 对于递归返回的到当前节点的一条最大路径，计算结果为：
     * node.val + max(left_gain, right_gain)。
     */
}
