package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class RecoverBSTree99 {

    /**
     * 99. 恢复二叉搜索树
     * 二叉搜索树中的两个节点被错误地交换。
     *
     * 请在不改变其结构的情况下，恢复这棵树。
     *
     * 示例 1:
     *
     * 输入: [1,3,null,null,2]
     *
     *    1
     *   /
     *  3
     *   \
     *    2
     *
     * 输出: [3,1,null,null,2]
     *
     *    3
     *   /
     *  1
     *   \
     *    2
     * @param root
     */
    public void recoverTree(TreeNode root) {
        List<Integer> trees = new ArrayList<>();
        inorder(root, trees);
        int[] twoSwaps = findTwoSwaps(trees);
        swapTree(root, 2, twoSwaps[0], twoSwaps[1]);
    }

    public void swapTree(TreeNode root, int count, int x, int y) {
        if (root == null) {
            return;
        }
        if (root.val == x || root.val == y) {
            root.val = (root.val == x) ? y : x;
            if (--count == 0) {
                return;
            }
        }
        swapTree(root.left, count, x, y);
        swapTree(root.right, count, x, y);
    }
    public void inorder(TreeNode root, List<Integer> trees) {
        if (root == null) {
            return;
        }
        inorder(root.left, trees);
        trees.add(root.val);
        inorder(root.right, trees);
    }

    public int[] findTwoSwaps(List<Integer> trees) {
        int x = -1, y = -1;
        for (int i = 0; i < trees.size()-1; i++) {
            if (trees.get(i) > trees.get(i+1)) {
                y = trees.get(i+1);
                if (x == -1) {
                    x = trees.get(i);
                } else {
                    break;
                }
            }
        }
        return new int[]{x, y};
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        (new RecoverBSTree99()).recoverTree(root);
    }

}
