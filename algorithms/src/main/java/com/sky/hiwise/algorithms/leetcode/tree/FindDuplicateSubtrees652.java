package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class FindDuplicateSubtrees652 {
    /**
     * 652. 寻找重复的子树
     * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，
     * 你只需要返回其中任意一棵的根结点即可。
     * 两棵树重复是指它们具有相同的结构以及相同的结点值。
     * 示例 1：
     *
     *         1
     *        / \
     *       2   3
     *      /   / \
     *     4   2   4
     *        /
     *       4
     * 下面是两个重复的子树：
     *
     *       2
     *      /
     *     4
     * 和
     *     4
     * 因此，你需要以列表的形式返回上述重复子树的根结点。
     */
    private HashMap<String, Integer> trees;
    private HashMap<Integer, Integer> count;
    List<TreeNode> ans;
    int t;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        trees = new HashMap<>();
        count = new HashMap<>();
        ans = new ArrayList<>();
        t = 1;
        lookup(root);
        return ans;
    }

    private int lookup(TreeNode root) {
        if (root == null) {
            return 0;
        }
        String flag = root.val + "," + lookup(root.right) + "," + lookup(root.left);
        int uid = trees.computeIfAbsent(flag, x-> t++);
        count.put(uid, count.getOrDefault(uid, 0) + 1);
        if (count.get(uid) == 2) {
            ans.add(root);
        }
        return uid;
    }
}
