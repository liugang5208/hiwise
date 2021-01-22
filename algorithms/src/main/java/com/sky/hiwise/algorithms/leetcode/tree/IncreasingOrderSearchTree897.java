package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class IncreasingOrderSearchTree897 {
    /**
     * 897. 递增顺序查找树
     * 给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。
     * 示例 ：
     * 输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]
     *
     *        5
     *       / \
     *     3    6
     *    / \    \
     *   2   4    8
     *  /        / \
     * 1        7   9
     * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     *  1
     *   \
     *    2
     *     \
     *      3
     *       \
     *        4
     *         \
     *          5
     *           \
     *            6
     *             \
     *              7
     *               \
     *                8
     *                 \
     *                  9
     */
    public List<Integer> list = new ArrayList<>();
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        inorder(root);
        TreeNode ans = new TreeNode(0);
        TreeNode curr = ans;
        for (int i : list) {
            curr.right = new TreeNode(i);
            curr = curr.right;
        }
        return ans.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        list.add(node.val);
        inorder(node.right);
    }

    TreeNode curr;
    public TreeNode increasingBST2(TreeNode root) {
        TreeNode ans = new TreeNode(0);
        curr = ans;
        inorder2(root);
        return ans.right;
    }

    public void inorder2(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder2(node.left);
        node.left = null;
        curr.right = node;
        curr = node;
        inorder2(node.right);
    }
}
