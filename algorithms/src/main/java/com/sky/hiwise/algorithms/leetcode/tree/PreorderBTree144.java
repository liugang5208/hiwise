package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class PreorderBTree144 {

    /**
     * 144. 二叉树的前序遍历
     * 给定一个二叉树，返回它的 前序 遍历。
     *
     *  示例:
     *
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,2,3]
     */
    private List<Integer> ans = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return ans;
        }
        preorder(root);
        return ans;
    }
    public void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        preorder(root.left);
        preorder(root.right);
    }
}
