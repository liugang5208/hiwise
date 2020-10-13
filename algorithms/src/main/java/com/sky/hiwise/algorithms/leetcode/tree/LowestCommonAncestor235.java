package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.*;

public class LowestCommonAncestor235 {

    /**
     * 235 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     *        6
     *      /   \
     *     2     8
     *    / \   /  \
     *   0   4 7   9
     *      / \
     *     3  5
     * 示例 1:
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * 输出: 6
     * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
     * 示例 2:
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * 输出: 2
     * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     * 说明:
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉搜索树中。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public InvertTree226.TreeNode lowestCommonAncestor(InvertTree226.TreeNode root, InvertTree226.TreeNode p, InvertTree226.TreeNode q) {
        if(p == null || q == null)
            throw new IllegalArgumentException("p or q can not be null.");

        if(root == null)
            return null;

        if(p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        if(p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);

        assert p.val == root.val || q.val == root.val
                || (root.val - p.val) * (root.val - q.val) < 0;

        return root;
    }
    /**
     * 算法
     * 从根节点开始遍历树
     * 如果节点 pp 和节点 qq 都在右子树上，那么以右孩子为根节点继续 1 的操作
     * 如果节点 pp 和节点 qq 都在左子树上，那么以左孩子为根节点继续 1 的操作
     * 如果条件 2 和条件 3 都不成立，这就意味着我们已经找到节 pp 和节点 qq 的 LCA 了
     */

    /**
     * 236 二叉树的最近公共祖先
     */
    private InvertTree226.TreeNode ans = null;
    public InvertTree226.TreeNode lowestCommonAncestor2(InvertTree226.TreeNode root, InvertTree226.TreeNode p, InvertTree226.TreeNode q) {
        // Traverse the tree
        this.recurseTree(root, p, q);
        return this.ans;
    }
    private boolean recurseTree(InvertTree226.TreeNode currentNode, InvertTree226.TreeNode p, InvertTree226.TreeNode q) {
        // If reached the end of a branch, return false.
        if (currentNode == null) {
            return false;
        }
        // Left Recursion. If left recursion returns true, set left = 1 else 0
        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;
        // Right Recursion
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;
        // If the current node is one of p or q
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;
        // If any two of the flags left, right or mid become True
        if (mid + left + right >= 2) {
            this.ans = currentNode;
        }
        // Return true if any one of the three bool values is True.
        return (mid + left + right > 0);
    }
    /**
     * 解决方法：
     * 首先在二叉树中搜索给定的节点 p 和 q，然后找到它们的最近共同祖先。我们可以使用普通的树遍历来搜索这两个节点。一旦我们达到所需的节点 p 和 q，我们就可以回溯并找到最近的共同祖先。
     * 方法一：递归
     * 这种方法非常直观。先深度遍历改树。当你遇到节点 p 或 q 时，返回一些布尔标记。该标志有助于确定是否在任何路径中找到了所需的节点。最不常见的祖先将是两个子树递归都返回真标志的节点。它也可以是一个节点，它本身是p或q中的一个，对于这个节点,子树递归返回一个真标志。
     * 让我们看看基于这个想法的形式算法。
     * 算法：
     * 从根节点开始遍历树。
     * 如果当前节点本身是 p 或 q 中的一个，我们会将变量 mid 标记为 true，并继续搜索左右分支中的另一个节点。
     * 如果左分支或右分支中的任何一个返回 true，则表示在下面找到了两个节点中的一个。
     * 如果在遍历的任何点上，左、右或中三个标志中的任意两个变为 true，这意味着我们找到了节点 p 和 q 的最近公共祖先。
     */

    /**
     * 236 二叉树的最近公共祖先
     * 方法二：使用父指针迭代
     * 如果每个节点都有父指针，那么我们可以从 p 和 q 返回以获取它们的祖先。在这个遍历过程中，我们得到的第一个公共节点是 LCA 节点。我们可以在遍历树时将父指针保存在字典中。
     * 算法：
     * 从根节点开始遍历树。
     * 在找到 p 和 q 之前，将父指针存储在字典中。
     * 一旦我们找到了 p 和 q，我们就可以使用父亲字典获得 p 的所有祖先，并添加到一个称为祖先的集合中。
     * 同样，我们遍历节点 q 的祖先。如果祖先存在于为 p 设置的祖先中，这意味着这是 p 和 q 之间的第一个共同祖先（同时向上遍历），因此这是 LCA 节点。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public InvertTree226.TreeNode lowestCommonAncestor3(InvertTree226.TreeNode root, InvertTree226.TreeNode p, InvertTree226.TreeNode q) {
        // Stack for tree traversal
        Deque<InvertTree226.TreeNode> stack = new ArrayDeque<>();
        // HashMap for parent pointers
        Map<InvertTree226.TreeNode, InvertTree226.TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        stack.push(root);
        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            InvertTree226.TreeNode node = stack.pop();
            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        // Ancestors set() for node p.
        Set<InvertTree226.TreeNode> ancestors = new HashSet<>();
        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }

    /**
     * 面试题 04.08. 首个共同祖先
     * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
     * https://leetcode-cn.com/problems/first-common-ancestor-lcci/
     */
}
