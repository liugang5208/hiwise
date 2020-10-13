package com.sky.hiwise.algorithms.leetcode.tree;

public class KthElementBST {

    /**
     * 230. 二叉搜索树中第K小的元素
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     * 示例 1:
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 1
     * 示例 2:
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * 输出: 3
     * 进阶：
     * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
     * @param root
     * @param k
     * @return
     */
    private int result, count;
    public int kthSmallest(InvertTree226.TreeNode root, int k) {
        count = k;
        inorder(root);
        return result;
    }
    private void inorder(InvertTree226.TreeNode root) {
        if(root == null || count == 0) return;
        inorder(root.left);
        if(--count == 0) result = root.val;
        inorder(root.right);
    }
    /**
     * 二叉搜索树BST有一个重要性质：中序遍历为排序数组，根据这个性质，我们可将问题转化为寻找中序遍历第 k 个节点的值；
     * 实现的方法是建立两个全局变量res和count，分别用于存储答案与计数：
     * 在每次访问节点时，执行count -1；
     * 当count == 0时，代表已经到达第 k个节点，此时记录答案至res；
     * 找到答案后，已经不用继续遍历，因此每次判断res是否为空，若不为空直接返回。
     */

    /**
     * 面试题54. 二叉搜索树的第k大节点
     * 给定一棵二叉搜索树，请找出其中第k大的节点。
     * 示例 1:
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 4
     * 示例 2:
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * 输出: 4
     */
    public int kthLargest(TreeNode root, int k) {
        count = k;
        kthLargest(root);
        return result;
    }
    public void kthLargest(TreeNode root){
        if (root == null || count == 0) {
            return;
        }
        kthLargest(root.right);
        count--;
        if (count == 0) {
            result = root.val;
        }
        kthLargest(root.left);
    }
    //对于二分搜索树（BST）先右 再根 再左 为到序
    //先左 再根 再右为升序
}
