package com.sky.hiwise.algorithms.leetcode.tree;

public class CountGoodNodesInBTree1448 {
    /**
     * 1448. 统计二叉树中好节点的数目
     * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
     * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
     * 示例 1：
     * 输入：root = [3,1,4,3,null,1,5]
     * 输出：4
     * 解释：图中蓝色节点为好节点。
     * 根节点 (3) 永远是个好节点。
     * 节点 4 -> (3,4) 是路径中的最大值。
     * 节点 5 -> (3,4,5) 是路径中的最大值。
     * 节点 3 -> (3,1,3) 是路径中的最大值。
     */
    int ans = 0;
    public int goodNodes(TreeNode root) {
        if (root != null) {
            count(root, Integer.MIN_VALUE);
        }
        return ans;
    }

    public void count(TreeNode node, int max) {
        if (node == null) {
            return;
        }
        if (node.val >= max) {
            ans++;
            max = node.val;
        }
        count(node.left, max);
        count(node.right, max);
    }
}
