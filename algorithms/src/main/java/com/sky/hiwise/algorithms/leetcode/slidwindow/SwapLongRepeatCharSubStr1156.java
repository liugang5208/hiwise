package com.sky.hiwise.algorithms.leetcode.slidwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2023-06-05 15:23
 **/
public class SwapLongRepeatCharSubStr1156 {

    /**
     * 1156. 单字符重复子串的最大长度
     * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
     * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
     * 示例 1：
     * 输入：text = "ababa"
     * 输出：3
     * 示例 2：
     * 输入：text = "aaabaaa"
     * 输出：6
     * @param text
     * @return
     */
    public int maxRepOpt1(String text) {
        Map<Character, Integer> cntMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            cntMap.put(c, cntMap.getOrDefault(c, 0) + 1);
        }
        int n = text.length();
        int res = 0;
        for (int i = 0; i < n;) {
            //找出连续的一段[i，j)
            int j = i;
            while (j < n && text.charAt(j) == text.charAt(i)) {
                j++;
            }
            int curCnt = j - i;
            //如果这一段长度小于该字符出现的总长度 并且前面和后面有空位 用curCnt+1更新答案
            if (curCnt < cntMap.getOrDefault(text.charAt(i), 0) && (j < n || i > 0)) {
                res = Math.max(res, curCnt + 1);
            }
            //找到这一段后面与之相隔的一个不同字符的另一段[j+1,k) 如果不存在则k=j+1
            int k = j + 1;
            while (k < n && text.charAt(k) == text.charAt(i)) {
                k++;
            }
            res = Math.max(res, Math.min(k - i, cntMap.getOrDefault(text.charAt(i), 0)));
            i = j;
        }
        return res;
    }
    /**
     * 方法一：滑动窗口
     * 思路与算法
     * 给定一个字符串，你需要选择两个字符进行交换，这个操作最多进行一次，要求使得仅包含相同字符的子串尽可能的长。例如
     * "bbababaaaa"，可以交换第2(下标从0 开始） 个字符a 与第5 个字符b，使得包含相同字符的子串最长为6，即"aaaaaa"。
     * 我们设n 为字符串text 的长度，下标从0 开始，现在有一段区间[i,j) （不包括j ）由相同字符a 构成，并且该区间两边不存在相同的字符a，
     * 而整个text 中a 的出现次数为count[a]，那么当count[a]>j−i ，并且i>0 或者j<n 时，可以将其他地方出现的a 与text[i−1] 或
     * text[j] 交换，从而得到更长的一段仅包含字符a 的子串。交换后，交换过来的a 可能会使得两段连续的a 拼接在一起，我们假设[i,j) 是前面的一段，当
     * text[j+1]=a 时，我们在找到后面的一段[j+1,k)，这两段拼接在一起构成更长的子串。注意，我们需要重新判断是否有多余的a 交换到中间来，因此将拼接后的长度
     * k−i 与count[a] 取最小值来更新答案。
     * 在实现过程中，我们可以使用滑动窗口算法来求解由相同字符构成的最长区间。
     */
}
