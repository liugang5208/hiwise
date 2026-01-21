package com.sky.hiwise.algorithms.leetcode.dynamic;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LargestEachTreeLevel515 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 515. 在每个树行中找最大值
     * 您需要在二叉树的每一行中找到最大的值。
     * 示例：
     * 输入:
     *           1
     *          / \
     *         3   2
     *        / \   \
     *       5   3   9
     *
     * 输出: [1, 3, 9]
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxValue = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.remove();
                maxValue = Math.max(curr.val, maxValue);
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            ans.add(maxValue);
        }
        return ans;
    }
}
