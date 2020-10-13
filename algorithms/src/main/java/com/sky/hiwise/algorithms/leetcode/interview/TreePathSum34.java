package com.sky.hiwise.algorithms.leetcode.interview;

import com.sky.hiwise.algorithms.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreePathSum34 {
    /**
     * 面试题34. 二叉树中和为某一值的路径
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
     *
     *
     *
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     * 返回:
     *
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     */
    private List<List<Integer>> ans;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ans = new ArrayList<>();
        path(root, sum, new ArrayList<>());
        return ans;
    }

    private void path(TreeNode node, int target,  List<Integer> temp) {
        if (node == null) {
            return;
        }
        temp.add(node.val);
        if (target == node.val && node.right == null && node.left == null) {
            ans.add(new ArrayList<>(temp));
        }
        path(node.left, target - node.val, temp);
        path(node.right, target - node.val, temp);
        temp.remove(temp.size() - 1);
    }


}
