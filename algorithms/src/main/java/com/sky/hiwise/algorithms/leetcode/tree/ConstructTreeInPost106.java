package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.Collections;
import java.util.HashMap;

public class ConstructTreeInPost106 {

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * 注意:
     * 你可以假设树中没有重复的元素。
     * 例如，给出
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 返回如下的二叉树：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * @param inorder
     * @param postorder
     * @return
     */
    int preIndex = 0;
    int[] postorder;
    int[] inorder;
    HashMap<Integer, Integer> idxMap = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        this.preIndex = postorder.length - 1;
        int idx = 0;
        for(int val : inorder) {
            idxMap.put(val, idx ++);
        }
        return helper(0, inorder.length);
    }

    public TreeNode helper(int inLeft, int inRight) {
        if (inLeft == inRight) {
            return null;
        }
        int rootVal = postorder[preIndex--];
        TreeNode root = new TreeNode(rootVal);
        int index = idxMap.get(rootVal);
        root.right = helper(index + 1, inRight);
        root.left = helper(inLeft, index);
        return root;
    }
    /**
     * 1.使用中序遍历数组作为左子树，右子树的分界查找，
     * 2.如果使用前序遍历构造，则顺序中，左，右
     * 3.如果使用后续遍历构造，则逆序，中，右，左
     */
}
