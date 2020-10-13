package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class NAryTreeOrder589 {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    /**
     * 590. N叉树的后序遍历
     * 给定一个 N 叉树，返回其节点值的后序遍历。
     * 例如，给定一个 3叉树 :
     * 返回其后序遍历: [5,6,3,2,4,1].
     * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
     */
    List<Integer> ans = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        preorder1(root);
        return ans;
    }

    public void preorder1(Node root) {
        if (root == null) {
            return ;
        }
        ans.add(root.val);
        for(Node node : root.children) {
            preorder1(node);
        }
    }

    public List<Integer> postorder(Node root) {
        postorder1(root);
        return ans;
    }

    public void postorder1(Node root) {
        if (root == null) {
            return;
        }
        for(Node node : root.children) {
            postorder1(node);
        }
        ans.add(root.val);
    }
}
