package com.sky.hiwise.algorithms.leetcode.tree;

public class BTreePruning814 {

    /**
     * 814. 二叉树剪枝
     * 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。
     * 返回移除了所有不包含 1 的子树的原二叉树。
     * ( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
     *
     * 示例1:
     * 输入: [1,null,0,0,1]
     * 输出: [1,null,0,null,1]
     * 解释:
     * 只有红色节点满足条件“所有不包含 1 的子树”。
     * 右图为返回的答案。
     */
    public TreeNode pruneTree(TreeNode root) {
        return containsOne(root) ? root : null;
    }

    public boolean containsOne(TreeNode node) {
        if (node == null) {
            return false;
        }
        boolean left = containsOne(node.left);
        boolean right = containsOne(node.right);
        if (!left) {
            node.left = null;
        }
        if (!right) {
            node.right = null;
        }
        return node.val == 1 || left || right;
    }
    /**
     * 递归：
     * 我们可以使用递归来解决这个问题。我们用 containsOne(node) 函数来判断以 node 为根的子树中是否包含 1，
     * 其不包含 1 当且仅当以 node 的左右孩子为根的子树均不包含 1，并且 node 节点本身的值也不为 1。
     * 如果 node 的左右孩子为根的子树不包含 1，那我们就需要把对应的指针置为空。
     * 例如当 node 的左孩子为根的子树不包含 1 时，我们将 node.left 置为 null。
     * 在递归结束之后，如果整颗二叉树都不包含 1，那么我们返回 null，否则我们返回原来的根节点。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/binary-tree-pruning/solution/er-cha-shu-jian-zhi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
