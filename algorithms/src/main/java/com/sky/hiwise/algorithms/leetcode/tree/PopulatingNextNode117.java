package com.sky.hiwise.algorithms.leetcode.tree;


import java.util.LinkedList;

public class PopulatingNextNode117 {

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node currNode = queue.poll();
                if (i > 0) {
                    pre.next = currNode;
                }
                pre = currNode;
                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if (currNode.right != null) {
                    queue.offer(currNode.right);
                }
            }
        }
        return root;
    }
}
