package com.sky.hiwise.algorithms.leetcode.tree;

public class PathSum437 {
    /**
     * 437. 路径总和 III
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * 示例 1：
     * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
     * 输出：3
     * 解释：和等于 8 的路径有 3 条，如图所示。
     * 示例 2：
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：3
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ans = rootSum(root, targetSum);
        ans += pathSum(root.left, targetSum);
        ans += pathSum(root.right, targetSum);
        return ans;
    }

    public int rootSum(TreeNode root, int targetSum) {
        int ret = 0;
        if (root == null) {
            return ret;
        }
        if (root.val == targetSum) {
            ret++;
        }
        ret += rootSum(root.left, targetSum - root.val);
        ret += rootSum(root.right, targetSum - root.val);
        return ret;
    }
    /**
     * 我们首先想到的解法是穷举所有的可能，我们访问每一个节点node，检测以node 为起始节点且向下延深的路径有多少种。
     * 我们递归遍历每一个节点的所有可能的路径，然后将这些路径数目加起来即为返回结果。
     *
     * 我们首先定义 rootSum(p,val) 表示以节点 p为起点向下且满足路径总和为 val 的路径数目。我们对二叉树上每个节点 p 求出 rootSum(p,targetSum)，然后对这些路径数目求和即为返回结果。
     * 我们对节点 p 求 rootSum(p,targetSum) 时，以当前节点 p 为目标路径的起点递归向下进行搜索。假设当前的节点 p 的值为 val，我们对左子树和右子树进行递归搜索，对节点 p 的左孩子节点 pl
     *  rootSum(pl,targetSum−val), 以及对右孩子节点pr rootSum(pr,  targetSum−val)
     *  节点 p 的 rootSum(p,targetSum) 即等于 rootSum(pl,targetSum−val) 与rootSum(pr,  targetSum−val) 之和，
     *  同时我们还需要判断一下当前节点 p 的值是否刚好等于targetSum。
     * 我们采用递归遍历二叉树的每个节点 p，对节点 p 求 rootSum(p,val)，然后将每个节点所有求的值进行相加求和返回。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/path-sum-iii/solution/lu-jing-zong-he-iii-by-leetcode-solution-z9td/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
