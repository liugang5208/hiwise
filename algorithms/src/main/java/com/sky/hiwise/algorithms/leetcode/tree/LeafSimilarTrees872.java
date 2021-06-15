package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTrees872 {
    /**
     * 872. 叶子相似的树
     * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
     *
     * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
     * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
     * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
     *
     * 示例 1：
     * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
     * 输出：true
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        if (root1 != null) {
            dfs(root1, list1);
        }
        List<Integer> list2 = new ArrayList<>();
        if (root2 != null) {
            dfs(root2, list2);
        }
        return list1.equals(list2);
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node.left == null && node.right == null) {
            list.add(node.val);
        } else {
            if (node.left != null) {
                dfs(node.left, list);
            }
            if (node.right != null) {
                dfs(node.right, list);
            }
        }
    }
}
