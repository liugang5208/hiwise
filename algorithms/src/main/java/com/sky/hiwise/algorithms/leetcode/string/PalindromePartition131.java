package com.sky.hiwise.algorithms.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition131 {

    /**
     * 131. 分割回文串
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     * 返回 s 所有可能的分割方案。
     * 示例:
     * 输入: "aab"
     * 输出:
     * [
     *   ["aa","b"],
     *   ["a","a","b"]
     * ]
     * @param s
     * @return
     */
    public boolean[][] dp;
    public String string;
    public List<List<String>> ans;
    public int len;
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        string = s;
        len = s.length();
        dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for(int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }

        }
        ans = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtracking(0, path);
        return ans;
    }

    public void backtracking(int start, List<String> path) {
        if (start == len) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < len; i++) {
            if (!dp[start][i]) {
                continue;
            }
            path.add(string.substring(start, i + 1));
            backtracking(i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 搜索问题主要使用回溯法。
     * 回溯法思考的步骤：
     * 1、画递归树；
     * 2、根据自己画的递归树编码。
     * 思考如何根据这棵递归树编码：
     * 1、每一个结点表示剩余没有扫描到的字符串，产生分支是截取了剩余字符串的前缀；
     * 2、产生前缀字符串的时候，判断前缀字符串是否是回文。
     * 如果前缀字符串是回文，则可以产生分支和结点；
     * 如果前缀字符串不是回文，则不产生分支和结点，这一步是剪枝操作。
     * 3、在叶子结点是空字符串的时候结算，此时从根结点到叶子结点的路径，就是结果集里的一个结果，使用深度优先遍历，记录下所有可能的结果。
     * 采用一个路径变量 path 搜索，path 全局使用一个（注意结算的时候，需要生成一个拷贝），因此在递归执行方法结束以后需要回溯，即将递归之前添加进来的元素拿出去；
     * path 的操作只在列表的末端，因此合适的数据结构是栈。
     * 在上一步，验证回文串那里，每一次都得使用“两边夹”的方式验证子串是否是回文子串。
     * 于是“用空间换时间”，利用「力扣」第 5 题：最长回文子串 的思路，利用动态规划把结果先算出来，
     * 这样就可以以 O(1)的时间复杂度直接得到一个子串是否是回文。
     */
}
