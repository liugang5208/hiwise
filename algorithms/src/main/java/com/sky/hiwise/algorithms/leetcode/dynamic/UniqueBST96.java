package com.sky.hiwise.algorithms.leetcode.dynamic;

import com.sky.hiwise.algorithms.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBST96 {

    public static void main(String[] args) {
    }

    /**
     * 96. 不同的二叉搜索树
     * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
     * 示例:
     * 输入: 3
     * 输出: 5
     * 解释:
     * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] g = new int[n+1];
        g[0] = 1;
        g[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                g[i] += g[j-1] * g[i-j];
            }
        }
        return g[n];
    }
    /**
     * 举例而言，创建以 33 为根、长度为 77 的不同二叉搜索树，整个序列是 [1, 2, 3, 4, 5, 6, 7][1,2,3,4,5,6,7]，
     * 我们需要从左子序列 [1, 2][1,2] 构建左子树，从右子序列 [4, 5, 6, 7][4,5,6,7] 构建右子树，然后将它们组合（即笛卡尔积）。
     * 对于这个例子，不同二叉搜索树的个数为 F(3, 7)F(3,7)。
     * 我们将 [1,2][1,2] 构建不同左子树的数量表示为 G(2)G(2), 从 [4, 5, 6, 7][4,5,6,7] 构建不同右子树的数量表示为 G(4)G(4)，
     * 注意到 G(n)G(n) 和序列的内容无关，只和序列的长度有关。于是，F(3,7) = G(2) \cdot G(4)F(3,7)=G(2)⋅G(4)。 因此，我们可以得到以下公式：
     * F(i, n) = G(i-1) \cdot G(n-i) \qquad \qquad (2)
     * F(i,n)=G(i−1)⋅G(n−i)(2)
     * 将公式 (1)(1)，(2)(2) 结合，可以得到 G(n)G(n) 的递归表达式：
     * G(n) = \sum_{i=1}^{n}G(i-1) \cdot G(n-i) \qquad \qquad (3)
     * G(n)=
     * i=1
     * ∑
     * n
     *  G(i−1)⋅G(n−i)(3)
     * 至此，我们从小到大计算 GG 函数即可，因为 G(n)G(n) 的值依赖于 G(0) \cdots G(n-1)G(0)⋯G(n−1)。
     */

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return getTrees(1, n);
    }

    public List<TreeNode> getTrees(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if (start > end) {
            trees.add(null);
            return trees;
        }
        for(int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = getTrees(start, i - 1);
            List<TreeNode> rightTrees = getTrees(i + 1, end);
            for(TreeNode left : leftTrees) {
                for(TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    trees.add(root);
                }
            }
        }
        return trees;
    }
    /**
     * 方法一：递归
     * 思路与算法
     * 二叉搜索树关键的性质是根节点的值大于左子树所有节点的值，小于右子树所有节点的值，且左子树和右子树也同样为二叉搜索树。因此在生成所有可行的二叉搜索树的时候，假设当前序列长度为 nn，如果我们枚举根节点的值为 ii，
     * 那么根据二叉搜索树的性质我们可以知道左子树的节点值的集合为 [1 \ldots i-1][1…i−1]，右子树的节点值的集合为 [i+1 \ldots n][i+1…n]。
     * 而左子树和右子树的生成相较于原问题是一个序列长度缩小的子问题，因此我们可以想到用递归的方法来解决这道题目。
     * 我们定义 generateTrees(start, end) 函数表示当前值的集合为 [\textit{start},\textit{end}][start,end]，返回序列 [\textit{start},\textit{end}][start,end] 生成的所有可行的二叉搜索树。
     * 按照上文的思路，我们考虑枚举 [\textit{start},\textit{end}][start,end] 中的值 ii 为当前二叉搜索树的根，那么序列划分为了 [\textit{start},i-1][start,i−1] 和 [i+1,\textit{end}][i+1,end] 两部分。
     * 我们递归调用这两部分，即 generateTrees(start, i - 1) 和 generateTrees(i + 1, end)，获得所有可行的左子树和可行的右子树，那么最后一步我们只要从可行左子树集合中选一棵，
     * 再从可行右子树集合中选一棵拼接到根节点上，并将生成的二叉搜索树放入答案数组即可。
     * 递归的入口即为 generateTrees(1, n)，出口为当 \textit{start}>\textit{end}start>end 的时候，当前二叉搜索树为空，返回空节点即可
     */

}
