package com.sky.hiwise.algorithms.leetcode.string;

import java.util.ArrayList;

public class ReverseWords151 {

    /**
     * 151. 翻转字符串里的单词
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     * 示例 1：
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     * 示例 2：
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 示例 3：
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String[] s1= s.split(" ");
        int start = 0;
        int end = s1.length - 1;
        while(start < end) {
            String temp = s1[start].trim();
            s1[start] = s1[end].trim();
            s1[end] = temp;
            start++;
            end--;
        }
        StringBuilder buf = new StringBuilder();
        for(int i = 0; i < s1.length; ++i) {
            if (s1[i].length() == 0) {
                continue;
            }
            if (i > 0) {
                buf.append(" ");
            }
            if (s1[i] != null) {
                buf.append(s1[i]);
            }
        }
        return buf.toString();
    }

    /**
     * 557. 反转字符串中的单词 III
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * 示例 1:
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc"
     * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     * @param s
     * @return
     */

    public String reverseWords5571(String s) {
        String words[] = s.split(" ");
        StringBuilder res=new StringBuilder();
        for (String word: words)
            res.append(new StringBuffer(word).reverse().toString() + " ");
        return res.toString().trim();
    }

    public String reverseWords557(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String[] words = split(s);
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(reverse(word) + " ");
        }
        return sb.toString().trim();
    }

    public String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public String[] split(String s) {
        ArrayList<String> words = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                words.add(word.toString());
                word = new StringBuilder();
            } else {
                word.append(s.charAt(i));
            }
        }
        words.add(word.toString());
        return words.toArray(new String[words.size()]);
    }
}
