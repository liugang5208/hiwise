package com.sky.hiwise.algorithms.leetcode.graph.dfs;

import com.sky.hiwise.algorithms.leetcode.tree.TreeNode;

public class DistributeCoinsInBTree979 {

    /**
     * 979. 在二叉树中分配硬币
     * 给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。
     * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。
     * 返回使每个结点上只有一枚硬币所需的移动次数。
     * 示例 1：
     * 输入：[3,0,0]
     * 输出：2
     * 解释：从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。
     * 示例 2：
     * @param root
     * @return
     */
    int ans;
    public int distributeCoins(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        ans += Math.abs(left) + Math.abs(right);
        return left + right + node.val - 1;
    }

    /**
     * 方法：深度优先搜索
     * 思路
     * 如果树的叶子仅包含 0 枚金币（与它所需相比，它的 过载量 为 -1），那么我们需要从它的父亲节点移动一枚金币到这个叶子节点上。
     * 如果说，一个叶子节点包含 4 枚金币（它的 过载量 为 3），那么我们需要将这个叶子节点中的 3 枚金币移动到别的地方去。
     * 总的来说，对于一个叶子节点，需要移动到它中或需要从它移动到它的父亲中的金币数量为 过载量 = Math.abs(num_coins - 1)。
     * 然后，在接下来的计算中，我们就再也不需要考虑这些已经考虑过的叶子节点了。
     * 算法
     * 我们可以用上述的方法来逐步构建我们的最终答案。定义 dfs(node) 为这个节点所在的子树中金币的 过载量，也就是这个子树中金币的数量减去这个子树中节点的数量。
     * 接着，我们可以计算出这个节点与它的子节点之间需要移动金币的数量为 abs(dfs(node.left)) + abs(dfs(node.right))，
     * 这个节点金币的过载量为 node.val + dfs(node.left) + dfs(node.right) - 1。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/distribute-coins-in-binary-tree/solution/zai-er-cha-shu-zhong-fen-pei-ying-bi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
