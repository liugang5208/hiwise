package com.sky.hiwise.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class FindModeInBST501 {

    /**
     * 501. 二叉搜索树中的众数
     * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
     *
     * 假定 BST 有如下定义：
     *
     * 结点左子树中所含结点的值小于等于当前结点的值
     * 结点右子树中所含结点的值大于等于当前结点的值
     * 左子树和右子树都是二叉搜索树
     * 例如：
     * 给定 BST [1,null,2,2],
     *
     *    1
     *     \
     *      2
     *     /
     *    2
     */
    public int currCount = 0;
    public int maxCount = 0;
    public TreeNode pre = null;
    public List<Integer> ans = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        inOrder(root);
        int[] res = new int[ans.size()];
        int index = 0;
        for (int num : ans) {
            res[index++] = num;
        }
        return res;
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        if (pre != null && pre.val == root.val) {
            currCount ++;
        } else {
            currCount = 1;
        }
        if (currCount > maxCount) {
            ans.clear();
            ans.add(root.val);
            maxCount = currCount;
        } else if (currCount == maxCount) {
            ans.add(root.val);
        }
        pre = root;
        inOrder(root.right);
    }
    /**
     * 解题思路
     * 核心思路
     * 本题中二叉搜索树的中序遍历结果是一个非递减的序列，所以进行中序遍历，通过比较前一个元素pre和当前元素cur的值确定是否执行操作，具体如下
     *
     * 如果pre不存在，说明目前是在第一个元素的位置，所以将计数置为1，并添加当前元素进入结果数组
     * 如果pre存在，先分两种情况计数
     * 如果pre->val == cur->val：说明当前值和前一个相同，计数加1,count++
     * 如果pre->val ！= cur->val：说明当前值和前一个不同，计数置为1,count = 1
     * 在得到计数count后，与当前最大计数maxnum比较：
     *
     * 如果count > maxnum : 更新maxnum，清空结果数组res，添加cur->val
     * 如果count == maxnum ：只需添加cur->val
     *
     * 作者：aspenstarss
     * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/solution/lian-di-gui-du-bu-yong-de-chang-shu-kong-jian-fu-z/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
