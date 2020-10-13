package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak139 {

    /**
     * 139. 单词拆分
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * 说明：
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     * 示例 1：
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     * 示例 2：
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     *      注意你可以重复使用字典中的单词。
     * 示例 3：
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     * @param s
     * @param wordDict
     * @return
     */
    Boolean[] memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return  false;
        }

        HashSet<String> wordSet = new HashSet<>(wordDict);
        memo = new Boolean[s.length()];
        return findWord(s, 0, wordSet);
    }

    public boolean findWord(String s, int start, Set<String> wordSet) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }

        for (int i = start + 1; i <= s.length(); i ++) {
            if (wordSet.contains(s.substring(start, i))
                    && findWord(s, i, wordSet)
            ) {
               return memo[start] = true;
            }
        }
        return  memo[start] = false;
    }

    /**
     * 方法 4：使用动态规划
     * 算法
     * 这个方法的想法是对于给定的字符串（ss）可以被拆分成子问题 s1 和 s2 。
     * 如果这些子问题都可以独立地被拆分成符合要求的子问题，那么整个问题 s 也可以满足。
     * 也就是，如果 "catsanddog" 可以拆分成两个子字符串 "catsand" 和 "dog" 。
     * 子问题 "catsand" 可以进一步拆分成 "cats" 和 "and" ，这两个独立的部分都是字典的一部分，
     * 所以 "catsand" 满足题意条件，再往前， "catsand" 和 "dog" 也分别满足条件，
     * 所以整个字符串 "catsanddog" 也满足条件。
     * 现在，我们考虑 dp 数组求解的过程。我们使用 n+1 大小数组的 dp ，其中 n 是给定字符串的长度。
     * 我们也使用 2 个下标指针 i 和 j ，其中 i 是当前字符串从头开始的子字符串（s'）的长度，
     * j 是当前子字符串（s'）的拆分位置，
     * 拆分成 s'(0,j)和 s'(j+1,i)
     * 为了求出dp 数组，我们初始化dp[0] 为true ，这是因为空字符串总是字典的一部分。
     * dp 数组剩余的元素都初始化为 false 。
     * 我们用下标 i 来考虑所有从当前字符串开始的可能的子字符串。
     * 对于每一个子字符串，我们通过下标 j 将它拆分成 s1'和s2' （注意 i 现在指向 s2'的结尾）。
     * 为了将 dp[i] 数组求出来，我们依次检查每个 dp[j] 是否为 true ，也就是子字符串 s1'是否满足题目要求。
     * 如果满足，我们接下来检查 s2'是否在字典中。如果两个字符串都满足要求，我们让 dp[i] 为 true ，否则令其为 false 。
     * @param args
     */
    boolean[] dp;
    public boolean wordBreak2(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int len = s.length();
        HashSet<String> wordSet = new HashSet<>(wordDict);
        dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }


    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println((new WordBreak139()).wordBreak(s, wordDict));
    }
}
