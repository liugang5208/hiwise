package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxDepthNTree559 {

    /**
     * 559. N叉树的最大深度
     * 给定一个 N 叉树，找到其最大深度。
     *
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     *
     * 例如，给定一个 3叉树 :
     *
     *
     */
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.children.isEmpty()) {
            return 1;
        }
        List<Integer> heigths = new ArrayList<>();
        for(Node item : root.children) {
            heigths.add(maxDepth(item));
        }

        return Collections.max(heigths) + 1;
    }
}
