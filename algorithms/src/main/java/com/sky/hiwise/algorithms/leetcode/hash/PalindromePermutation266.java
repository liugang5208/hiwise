package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.HashSet;
import java.util.Set;

public class PalindromePermutation266 {

    /**
     * 266 Palindrome Permutation 回文全排列
     * Given a string, determine if a permutation of the string could form a palindrome.
     * Example 1:
     * Input: "code"
     * Output: false
     * Example 2:
     * Input: "aab"
     * Output: true
     * Example 3:
     * Input: "carerac"
     * Output: true
     * Hint:
     * Consider the palindromes of odd vs even length. What difference do you notice?
     * Count the frequency of each character.
     * If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
     * 这道题让我们判断一个字符串的全排列有没有是回文字符串的，
     * 那么根据题目中的提示，我们分字符串的个数是奇偶的情况来讨论，如果是偶数的话，由于回文字符串的特性，每个字母出现的次数一定是偶数次，
     * 当字符串是奇数长度时，只有一个字母出现的次数是奇数，其余均为偶数，
     * 那么利用这个特性我们就可以解题，我们建立每个字母和其出现次数的映射，然后我们遍历 HashMap，统计出现次数为奇数的字母的个数，
     * 那么只有两种情况是回文数，第一种是没有出现次数为奇数的字母，再一个就是字符串长度为奇数，且只有一个出现次数为奇数的字母，参见代码如下：
     *
     * 那么我们再来看一种解法，这种方法用到了一个 HashSet，我们遍历字符串，
     * 如果某个字母不在 HashSet 中，我们加入这个字母，如果字母已经存在，我们删除该字母，
     * 那么最终如果 HashSet 中没有字母或是只有一个字母时，说明是回文串，参见代码如下：
     *
     *
     * @param s
     * @return
     */
    public static boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            Character character = c;
            if (!set.contains(character)) {
                set.add(character);
            } else {
                set.remove(character);
            }
        }
        return set.isEmpty() || set.size() == 1;
    }

    public static void main(String[] args) {
        System.out.println(canPermutePalindrome("qqqwwer"));
    }
}
