package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.*;

public class AllDistanceKBTree863 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 863. 二叉树中所有距离为 K 的结点
     * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
     * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
     * 示例 1：
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
     * 输出：[7,4,1]
     * 解释：
     * 所求结点为与目标结点（值为 5）距离为 2 的结点，
     * 值分别为 7，4，以及 1
     */
    HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        dfs(root, null);
        HashMap<TreeNode, Integer> visited = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        visited.put(target, 0);

        List<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()) {
            int size = queue.size();
            TreeNode node = queue.poll();
            int level = visited.get(node);
            if (level == K) {
                ans.add(node.val);
                for (int i = 0; i < size - 1; i++) {
                    TreeNode curr = queue.poll();
                    if (curr != null) {
                        ans.add(curr.val);
                    }
                }
                return ans;
            } else {
                if (node.left != null && !visited.containsKey(node.left)) {
                    queue.add(node.left);
                    visited.put(node.left, level + 1);
                }
                if (node.right != null && !visited.containsKey(node.right)) {
                    queue.add(node.right);
                    visited.put(node.right, level + 1);
                }
                if (parentMap.get(node) != null && !visited.containsKey(parentMap.get(node))) {
                    queue.add(parentMap.get(node));
                    visited.put(parentMap.get(node), level + 1);
                }
            }
        }

        return new ArrayList<>();
    }

    private void dfs(TreeNode curr, TreeNode parent) {
        if (curr != null) {
            parentMap.put(curr, parent);
            dfs(curr.left, curr);
            dfs(curr.right, curr);
        }
    }
}
