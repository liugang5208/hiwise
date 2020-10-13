package com.sky.hiwise.algorithms.leetcode.tree;

public class RangeSumBST938 {

    /**
     * 938. 二叉搜索树的范围和
     * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
     * 二叉搜索树保证具有唯一的值。
     * 示例 1：
     * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
     * 输出：32
     * 示例 2：
     * 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
     * 输出：23
     */
    int ans = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        dfs(root, L, R);
        return ans;
    }

    public void dfs(TreeNode root, int L, int R) {
        if (root != null) {
            if (root.val >= L && root.val <= R) {
                ans += root.val;
            }
            if (root.val >= L) {
                dfs(root.left, L, R);
            }
            if (root.val <= R) {
                dfs(root.right, L, R);
            }
        }
    }

    /**
     * 解题思路
     * 标签：深度优先遍历
     * 题意：这个题字面含义很难理解，本意就是求出所有 X >= L 且 X <= R 的值的和
     * 递归终止条件：
     * 当前节点为 null 时返回 0
     * 当前节点 X < L 时则返回右子树之和
     * 当前节点 X > R 时则返回左子树之和
     * 当前节点 X >= L 且 X <= R 时则返回：当前节点值 + 左子树之和 + 右子树之和
     * 注意点：通过判断X的大小能够避免遍历全部树的节点，比如下方的动图中，3 这个值就没有必要遍历
     *
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/range-sum-of-bst/solution/hua-jie-suan-fa-938-er-cha-sou-suo-shu-de-fan-wei-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int rangeSumBST1(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val < L) {
            return rangeSumBST1(root.right, L, R);
        }
        if (root.val > R) {
            return rangeSumBST1(root.left, L, R);
        }
        return root.val + rangeSumBST1(root.left, L, R) + rangeSumBST1(root.right, L, R);
    }
}
