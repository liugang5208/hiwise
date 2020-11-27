package com.sky.hiwise.algorithms.leetcode.tree;

public class PseudoPalindromicPaths1457 {

    /**
     * 1457. 二叉树中的伪回文路径
     * 给你一棵二叉树，每个节点的值为 1 到 9 。我们称二叉树中的一条路径是 「伪回文」的，当它满足：路径经过的所有节点值的排列中，存在一个回文序列。
     * 请你返回从根到叶子节点的所有路径中 伪回文 路径的数目。
     * 示例 1：
     * 输入：root = [2,3,1,3,1,null,1]
     * 输出：2
     * 解释：上图为给定的二叉树。总共有 3 条从根到叶子的路径：红色路径 [2,3,3] ，绿色路径 [2,1,1] 和路径 [2,3,1] 。
     *      在这些路径中，只有红色和绿色的路径是伪回文路径，因为红色路径 [2,3,3] 存在回文排列 [3,2,3] ，绿色路径 [2,1,1] 存在回文排列 [1,2,1] 。
     */
    int ans = 0;
    public int pseudoPalindromicPaths (TreeNode root) {
        if (root != null) {
            dfs(root, 0);
        }
        return ans;
    }

    public void dfs(TreeNode node, int temp) {
        temp ^= (1<<node.val);
        if (node.left == null && node.right == null) {
            if (temp == 0 || (temp & (temp - 1)) == 0) {
                ans++;
            }
        }
        if (node.left != null) {
            dfs(node.left, temp);
        }
        if (node.right != null) {
            dfs(node.right, temp);
        }
    }
    /**
     * 解题思路
     * 这题主要难点时如何确定路径为伪回文。
     * 题中节点的值只能为1-9,充分利用这点。
     * 我们用一个二进制数来维护判断是不是伪回文。
     * 二进制数第一位的1，0表示值为1的节点奇偶性，第二位1，0表示值为2的节点奇偶性。。。
     * 按 2,3,3路径来说。
     * 2节点来的时候 temp 的二进制为00000010; temp^=1<<2;
     * 3节点来的时候 temp 的二进制为00000110; temp^=1<<3;
     * 3节点再来的时候 temp 的二进制为00000010; temp^=1<<3;
     * 当到达叶子节点的时候，如果有偶数个元素,如果是伪回文，temp==0;
     * 奇数个时，temp的二进制有一位为1.temp&(temp-1)==0
     * 作者：rational-irrationality
     * 链接：https://leetcode-cn.com/problems/pseudo-palindromic-paths-in-a-binary-tree/solution/java-dfs-shuang-bai-by-rational-irrationality/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

}
