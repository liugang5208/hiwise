package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidthBTree662 {

    /**
     * 662. 二叉树最大宽度
     * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
     *
     * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
     *
     * 示例 1:
     *
     * 输入:
     *
     *            1
     *          /   \
     *         3     2
     *        / \     \
     *       5   3     9
     *
     * 输出: 4
     * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
     */
    class AnnotatedNode {
        TreeNode node;
        int pos;
        AnnotatedNode(TreeNode n, int p) {
            node = n;
            pos = p;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<AnnotatedNode> queue = new LinkedList<>();
        queue.add(new AnnotatedNode(root, 0));
        int ans = 0;
        int left = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                AnnotatedNode anode = queue.poll();
                TreeNode currNode = anode.node;
                if (i == 0) {
                    left = anode.pos;
                }
                ans = Math.max(ans, anode.pos - left + 1);
                if (currNode.left != null) {
                    queue.add(new AnnotatedNode(currNode.left, anode.pos * 2));
                }
                if (currNode.right != null) {
                    queue.add(new AnnotatedNode(currNode.right, anode.pos * 2 + 1));
                }
            }
        }
        return ans;
    }
}
