package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EvenOddTree1609 {

    /**
     * 1609. 奇偶树
     * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
     * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
     * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
     * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
     * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
     * 示例 1：
     * 输入：root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
     * 输出：true
     * 解释：每一层的节点值分别是：
     * 0 层：[1]
     * 1 层：[10,4]
     * 2 层：[3,7,9]
     * 3 层：[12,8,6,2]
     * 由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。
     * @param root
     * @return
     */
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            boolean odd = level % 2 == 1; //奇数
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                list.add(curr.val);
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }

            }
            if (list.size() > 0) {
                if ((list.get(0) % 2 == 1) == odd) {
                    return false;
                }
            }
            for (int i = 1; i < list.size(); i++) {
                if (odd && (list.get(i) >= list.get(i - 1) || list.get(i) % 2 == 1)) {
                    return false;
                } else if (!odd && (list.get(i) <= list.get(i - 1) || list.get(i) % 2 == 0)) {
                    return false;
                }
            }
            level++;
        }
        return true;
    }

    public static void main(String[] args) {
        //int[] root = new int[]{1,10,4,3,null,7,9,12,8,6,null,null,2};
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.left.left = new TreeNode(12);
        root.left.left.right = new TreeNode(8);
        root.right.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(2);
        System.out.println((new EvenOddTree1609()).isEvenOddTree(root));

        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(4);
        node.right = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(3);
        node.right.left = new TreeNode(7);
        System.out.println((new EvenOddTree1609()).isEvenOddTree(node));

        TreeNode node1 = new TreeNode(5);
        node1.left = new TreeNode(9);
        node1.right = new TreeNode(1);
        node1.left.left = new TreeNode(3);
        node1.left.right = new TreeNode(5);
        node1.right.left = new TreeNode(7);
        System.out.println((new EvenOddTree1609()).isEvenOddTree(node1));

    }
}
