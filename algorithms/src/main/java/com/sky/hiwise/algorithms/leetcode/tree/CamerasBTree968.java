package com.sky.hiwise.algorithms.leetcode.tree;

public class CamerasBTree968 {

    /**
     * 968. 监控二叉树
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     * 计算监控树的所有节点所需的最小摄像头数量。
     * 示例 1：
     * 输入：[0,0,null,0,0]
     * 输出：1
     * 解释：如图所示，一台摄像头足以监控所有节点。
     * @param root
     * @return
     */
    public int minCameraCover(TreeNode root) {
        int[] array = dfs(root);
        return array[1];
    }

    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int[] arr = new int[3];
        arr[0] = left[2] + right[2] + 1;
        arr[1] = Math.min(arr[0], Math.min(left[0] + right[1], left[1] + right[0]));
        arr[2] = Math.min(arr[0], left[1] + right[1]);
        return arr;
    }
    /**
     * 假设当前节点为 \textit{root}root，其左右孩子为 \textit{left}, \textit{right}left,right。如果要覆盖以 \textit{root}root 为根的树，有两种情况：
     * 若在 \textit{root}root 处安放摄像头，则孩子 \textit{left}, \textit{right}left,right 一定也会被监控到。此时，只需要保证 \textit{left}left 的两棵子树被覆盖，同时保证 \textit{right}right 的两棵子树也被覆盖即可。
     * 否则， 如果 \textit{root}root 处不安放摄像头，则除了覆盖 \textit{root}root 的两棵子树之外，孩子 \textit{left}, \textit{right}left,right 之一必须要安装摄像头，从而保证 \textit{root}root 会被监控到。
     * 根据上面的讨论，能够分析出，对于每个节点 \textit{root}root ，需要维护三种类型的状态：
     * 状态 aa：\textit{root}root 必须放置摄像头的情况下，覆盖整棵树需要的摄像头数目。
     * 状态 bb：覆盖整棵树需要的摄像头数目，无论 \textit{root}root 是否放置摄像头。
     * 状态 cc：覆盖两棵子树需要的摄像头数目，无论节点 \textit{root}root 本身是否被监控到。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/binary-tree-cameras/solution/jian-kong-er-cha-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

}
