package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.HashMap;

public class ConstructTreePreIn105 {

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     * 注意:
     * 你可以假设树中没有重复的元素。
     * 例如，给出
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    int preIndex = 0;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> idxMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        this.preorder = preorder;
        this.inorder = inorder;
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
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        int index = idxMap.get(rootVal);
        root.left = helper(inLeft, index);
        root.right = helper(index + 1, inRight);
        return root;
    }
    /**
     * 如上文所提到的，先序遍历的顺序是 Root -> Left -> Right，这就能方便的从根开始构造一棵树。
     * 首先，preorder 中的第一个元素一定是树的根，这个根又将 inorder 序列分成了左右两棵子树。
     * 现在我们只需要将先序遍历的数组中删除根元素，然后重复上面的过程处理左右两棵子树。
     */
}
