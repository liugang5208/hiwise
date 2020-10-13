package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.*;

public class InvertTree226 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.right) + 1, maxDepth(root.left) + 1);
    }

    /**
     * 226. 翻转二叉树
     * 翻转一棵二叉树。
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 转换为：
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     * 备注:
     * 这个问题是受到 Max Howell 的 原问题 启发 ：
     * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您无法在白板上写出翻转二叉树这道题，这太糟糕了。
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return root;
        }
        invertTree(root.left);
        invertTree(root.right);

        //反转
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        return root;
    }

    /**
     * 100. 相同的树
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * 示例 1:
     * 输入:       1         1
     *           / \       / \
     *          2   3     2   3
     *
     *         [1,2,3],   [1,2,3]
     * 输出: true
     * 示例 2:
     * 输入:      1          1
     *           /           \
     *          2             2
     *
     *         [1,2],     [1,null,2]
     * 输出: false
     *
     * 示例 3:
     * 输入:       1         1
     *           / \       / \
     *          2   1     1   2
     *
     *         [1,2,1],   [1,1,2]
     * 输出: false
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }

    /**
     * 112. 路径总和
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \      \
     *         7    2      1
     *
     * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
       return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例:
     * 给定二叉树 [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最小深度  2.
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.right != null && root.left != null) {
            return Math.min(minDepth(root.right) + 1, minDepth(root.left) + 1);
        } else if (root.right != null ) {
            return minDepth(root.right) + 1;
        } else if (root.left != null) {
            return minDepth(root.left) + 1;
        }
        return 1;
    }

    /**
     * 101. 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     *
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     *
     * 说明:
     * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isMirrorTree(root, root);
    }

    private boolean isMirrorTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isMirrorTree(p.right, q.left) && isMirrorTree(p.left, q.right);
    }

    /**
     * 222. 完全二叉树的节点个数
     * 给出一个完全二叉树，求出该树的节点个数。
     * 完全二叉树的定义如下：
     * 在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含1~ 2h 个节点。（堆使用完全二叉树）
     * 满二叉树：
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 110. 平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     * 示例 1:
     * 给定二叉树 [3,9,20,null,null,15,7]
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回 true 。
     * 示例 2:
     * 给定二叉树 [1,2,2,3,3,null,null,4,4]
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     *
     * 返回 false 。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(countTreeHigh(root.left) - countTreeHigh(root.right)) > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    /**
     * 计算二叉树的高度
     * @param root
     * @return
     */
    private int countTreeHigh(TreeNode root) {
        if (root == null) {
          return 0;
        } else {
            return Math.max(countTreeHigh(root.right), countTreeHigh(root.left)) + 1;
        }
    }

    /**
     * 404. 左叶子之和
     * 计算给定二叉树的所有左叶子之和。
     * 示例：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return root.right == null ? 0 : sumOfLeftLeaves(root.right);
        } else {
            if (root.left.left == null && root.left.right == null) {
                return  root.left.val + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
            } else {
                return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
            }
        }
    }

    /**
     * 257. 二叉树的所有路径
     * 给定一个二叉树，返回从根节点到叶节点的所有路径。
     * 例如，给定以下二叉树:
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     *
     * 所有根到叶路径是:
     * ["1->2->5", "1->3"]
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        if (root.left == null && root.right == null) {
            list.add(String.valueOf(root.val));
            return list;
        }

        List<String> listRight = binaryTreePaths(root.right);
        for (String rStr : listRight) {
            list.add(root.val + "->" + rStr);
        }
        List<String> listLeft = binaryTreePaths(root.left);
        for (String lStr : listLeft) {
            list.add(root.val + "->" + lStr);
        }
        return list;
    }

    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> res=new ArrayList<String>();
        process(res, root, "");
        return res;
    }

    public void process(List<String> list,TreeNode node,String str){
        if (node == null){
            return;
        }
        str += (node.val+"");
        if (node.left != null){
            process(list, node.left, str+"->");
        }
        if (node.right != null){
            process(list, node.right, str+"->");
        }
        if (node.left == null && node.right == null){
            list.add(str);
        }
    }

    /**
     * 113. 路径总和 II
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path(res, root, sum, path);
        return res;
    }

    private void path(List<List<Integer>> res, TreeNode root, int sum, List<Integer> path) {
        if (root == null) {
            return ;
        }
        path.add(root.val);
        if (sum - root.val == 0 && root.right == null && root.left == null) {
            res.add(new ArrayList<>(path));
        }


        path(res, root.left, sum - root.val, path);
        path(res, root.right, sum - root.val, path);
        path.remove(path.size() -1);
    }

    List<List<Integer>> res=new ArrayList<>();
    List<Integer> list=new ArrayList<>();
    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        if(root == null)
            return res;
        list.add(root.val);
        sum=sum-root.val;

        //判断是否到达根  并且路径和等于sum了
        if (sum==0&&root.left==null&&root.right==null) {
            res.add(new ArrayList<>(list));   //加入此条路径
        }
        //递归调用
        pathSum(root.left,sum);
        pathSum(root.right,sum);

        list.remove(list.size()-1);
        return res;
    }

    /**
     * 129. 求根到叶子节点数字之和
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     * 计算从根到叶子节点生成的所有数字之和。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例 1:
     * 输入: [1,2,3]
     *     1
     *    / \
     *   2   3
     * 输出: 25
     * 解释:
     * 从根到叶子节点路径 1->2 代表数字 12.
     * 从根到叶子节点路径 1->3 代表数字 13.
     * 因此，数字总和 = 12 + 13 = 25.
     * 示例 2:
     * 输入: [4,9,0,5,1]
     *     4
     *    / \
     *   9   0
     *  / \
     * 5   1
     * 输出: 1026
     * 解释:
     * 从根到叶子节点路径 4->9->5 代表数字 495.
     * 从根到叶子节点路径 4->9->1 代表数字 491.
     * 从根到叶子节点路径 4->0 代表数字 40.
     * 因此，数字总和 = 495 + 491 + 40 = 1026.
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumNumbers(root, 0);
    }

    private int sumNumbers(TreeNode root, int k) {
        k = k * 10 + root.val;
        if (root.right == null && root.left == null) {
            return k;
        }
        int rigntSum = 0,leftSum = 0;
        if (root.right != null) {
            rigntSum = sumNumbers(root.right, k);
        }
        if (root.left != null) {
            leftSum = sumNumbers(root.left, k);
        }
        return rigntSum + leftSum;
    }

    /**
     * 437 给定一个二叉树，它的每个结点都存放着一个整数值。
     * 找出路径和等于给定数值的路径总数。
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     * 示例：
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     * 返回 3。和等于 8 的路径有:
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3.  -3 -> 11
     * @param root
     * @param sum
     * @return
     */
    // 在以root为根节点的二叉树中,寻找和为sum的路径,返回这样的路径个数
    public int pathSum3(TreeNode root, int sum) {
        if(root == null)
            return 0;
        return findPath(root, sum)
                + pathSum3(root.left , sum)
                + pathSum3(root.right , sum);
    }

    // 在以node为根节点的二叉树中,寻找包含node的路径,和为sum
    // 返回这样的路径个数
    private int findPath(TreeNode node, int num){
        if(node == null)
            return 0;

        int res = 0;
        if(node.val == num)
            res += 1;

        res += findPath(node.left , num - node.val);
        res += findPath(node.right , num - node.val);
        return res;
    }
    /**
     * 解法思路（一）
     * 路径的开头可以不是根节点，结束也可以不是叶子节点，是不是有点复杂？
     * 如果问题是这样：找出以根节点为开始，任意节点可作为结束，且路径上的节点和为 sum 的路径的个数；
     * 是不是前序遍历一遍二叉树就可以得到所有这样的路径？是的；
     * 如果这个问题解决了，那么原问题可以分解成多个这个问题；
     * 是不是和数线段是同一个问题，只不过线段变成了二叉树；
     * 在解决了以根节点开始的所有路径后，就要找以根节点的左孩子和右孩子开始的所有路径，三个节点构成了一个递归结构；
     * 递归真的好简单又好难；
     * 解法实现（一）
     * 时间复杂度
     * O(n)，n为树的节点个数；
     * 空间复杂度
     * O(h)，h为树的高度；
     */



    /**
     * 98 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * 假设一个二叉搜索树具有如下特征：
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     * 示例 2:
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (! helper(node.right, val, upper)) return false;
        if (! helper(node.left, lower, val)) return false;
        return true;
    }
    /**
     * 乍一看，这是一个平凡的问题。只需要遍历整棵树，检查 node.right.val > node.val 和
     * node.left.val < node.val 对每个结点是否成立。
     * 问题是，这种方法并不总是正确。不仅右子结点要大于该节点，整个右子树的元素都应该大于该节点。
     * 这意味着我们需要在遍历树的同时保留结点的上界与下界，在比较时不仅比较子结点的值，也要与上下界比较。
     */

    /**
     * 450 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * 一般来说，删除节点可分为两个步骤：
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
     * 示例:
     * root = [5,3,6,2,4,null,7]
     * key = 3
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
     * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
     *     5
     *    / \
     *   4   6
     *  /     \
     * 2       7
     * 另一个正确答案是 [5,2,6,null,4,null,7]。
     *     5
     *    / \
     *   2   6
     *    \   \
     *     4   7
     * @param root 用前驱结点（左子树中最大结点）代替被删除结点
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }

        assert key == root.val;

        if (root.left == null) {
            TreeNode right = root.right;
            root.right = null;
            return right;
        }

        if (root.right == null) {
            TreeNode left = root.left;
            root.left = null;
            return left;
        }
        TreeNode predecessor = maximum(root.left);
        TreeNode predecessorCopy = new TreeNode(predecessor.val);
        predecessorCopy.left = removeMax(root.left);
        predecessorCopy.right = root.right;
        root.left = null;
        root.right = null;
        return predecessorCopy;
    }

    private TreeNode removeMax(TreeNode node) {
        if (node.right == null) {
            TreeNode left = node.left;
            node.left = null;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    private TreeNode maximum(TreeNode node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 用后继结点（右子树中最小结点）代替被删除结点
     */
    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val < key) {
            root.right = deleteNode2(root.right, key);
            return root;
        } else if (root.val > key) {
            root.left = deleteNode2(root.left, key);
            return root;
        } else {
            // 如果待删除的节点左孩子为空
            if (root.left == null) {
                TreeNode rightNode = root.right;
                root.right = null;
                return rightNode;
            }
            // 如果待删除的节点只有右孩子
            if (root.right == null) {
                TreeNode leftNode = root.left;
                root.left = null;
                return leftNode;
            }
            // 从它的右子树中拿到最小的
            TreeNode successor = new TreeNode(minNode(root.right).val);
            successor.left = root.left;
            successor.right = removeMin(root.right);
            root.left = null;
            root.right = null;
            return successor;
        }
    }

    private TreeNode minNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    /**
     * 删除一个二分搜索树中最小的节点，把新的二分搜索树的根返回回去
     * 使用递归，要特别注意，定义的递归函数，返回的是，删除了最小值节点以后的新的二分搜索树的根
     * @param node
     * @return
     */
    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            // 就是那个我们要删除的节点
            TreeNode rightNode = node.right;
            node.right = null;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
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
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        inorder(root);
        return result;
    }
    private void inorder(TreeNode root) {
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

}
