package com.sky.hiwise.algorithms.leetcode.interview;

import com.sky.hiwise.algorithms.leetcode.tree.TreeNode;

public class Successor0406 {

    /**
     * 面试题 04.06. 后继者
     * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
     * 如果指定节点没有对应的“下一个”节点，则返回null。
     * 示例 1:
     * 输入: root = [2,1,3], p = 1
     *   2
     *  / \
     * 1   3
     * 输出: 2
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode left = inorderSuccessor(root.left, p);
            return left != null ? left : root;
        }
    }
}
