package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BTreeRightView199 {
    /**
     * 199. 二叉树的右视图
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * 示例:
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1, 3, 4]
     * 解释:
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++ ) {
                TreeNode node = queue.poll();
                //每层按照先右后左的顺序入队 每层只取第一个即最右边的
                if (i == 0) {
                    ans.add(node.val);
                }
                if (node.right != null) {
                    queue.add(node.right );
                }
                if (node.left != null) {
                    queue.add(node.left );
                }
            }
        }
        return ans;
    }
}
