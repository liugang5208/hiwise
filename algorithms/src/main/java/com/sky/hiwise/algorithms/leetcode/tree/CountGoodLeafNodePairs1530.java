package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class CountGoodLeafNodePairs1530 {
    /**
     * 1530. 好叶子节点对的数量
     * 给你二叉树的根节点 root 和一个整数 distance 。
     * 如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。
     * 返回树中 好叶子节点对的数量 。
     * 示例 1：
     * 输入：root = [1,2,3,null,4], distance = 3
     * 输出：1
     * 解释：树的叶节点是 3 和 4 ，它们之间的最短路径的长度是 3 。这是唯一的好叶子节点对。
     */
    int ans;
    public int countPairs(TreeNode root, int distance) {
        ans = 0;
        dfs(root, distance);
        return ans;
    }

    public List<Integer> dfs(TreeNode node, int distance) {
        List<Integer> ret = new ArrayList<>();
        if (node == null) {
            return ret;
        }
        if (node.left == null && node.right == null) {
            ret.add(0);
            return ret;
        }
        List<Integer> left = dfs(node.left, distance);
        for (int e : left) {
            if (++e > distance) {
                continue;
            }
            ret.add(e);
        }

        List<Integer> right = dfs(node.right, distance);
        for (int e : right) {
            if (++e > distance) {
                continue;
            }
            ret.add(e);
        }

        for (int l : left) {
            for(int r : right) {
                if (l + r + 2 <= distance) {
                    ans++;
                }
            }
        }
        return ret;
    }
    /**
     * 思路
     * root->val 没用，父节点和子节点的距离是 1
     * 对树后序遍历 ，需要返回这个节点到其下方所有叶子节点的距离
     * 这样就可以将这个节点的左子树所有叶子节点和右子树所有叶子节点都凑个对
     * 然后将所有叶子节点不超过距离的弄到一起返回
     * 图解
     * 作者：ikaruga
     * 链接：https://leetcode-cn.com/problems/number-of-good-leaf-nodes-pairs/solution/good-leaf-nodes-pairs-by-ikaruga/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
