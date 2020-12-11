package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import com.sky.hiwise.algorithms.leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class CousinsBTree993 {
    /**
     * 993. 二叉树的堂兄弟节点
     * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
     * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
     * 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
     * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
     * 示例 1：
     * 输入：root = [1,2,3,4], x = 4, y = 3
     * 输出：false
     * 示例 2：
     * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
     * 输出：true
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null || root.val == x || root.val == y) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode xNode = null, yNode = null, xFather = null, yFather = null;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                TreeNode currNode = queue.poll();
                if (currNode.left != null) {
                    queue.add(currNode.left);
                    if (currNode.left.val == x) {
                        xNode = currNode.left;
                        xFather = currNode;
                    }
                    if (currNode.left.val == y) {
                        yNode = currNode.left;
                        yFather = currNode;
                    }
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                    if (currNode.right.val == x) {
                        xNode = currNode.right;
                        xFather = currNode;
                    }
                    if (currNode.right.val == y) {
                        yNode = currNode.right;
                        yFather = currNode;
                    }
                }
                if (xNode == null && yNode == null) {
                    //两个节点都没找到，什么也不做
                } else if (xNode != null && yNode != null) {
                    //两个节点都找到了，那么判断它们是不是堂兄弟节点
                    //如果父亲结点不相等，说明是堂兄弟结点
                    return xFather != yFather;
                } else if (size == 0) {
                    //这层遍历完了，但是有一个节点找到了，另外一个没找到
                    return false;
                }
            }
        }
        return false;
    }
}
