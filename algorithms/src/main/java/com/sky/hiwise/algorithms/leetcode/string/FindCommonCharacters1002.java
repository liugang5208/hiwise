package com.sky.hiwise.algorithms.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCommonCharacters1002 {

    /**
     * 1002. 查找常用字符
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     * 你可以按任意顺序返回答案。
     * 示例 1：
     * 输入：["bella","label","roller"]
     * 输出：["e","l","l"]
     * 示例 2：
     * 输入：["cool","lock","cook"]
     * 输出：["c","o"]
     * @param A
     * @return
     */
    public List<String> commonChars(String[] A) {
        int[] minFreq = new int[26];
        Arrays.fill(minFreq, Integer.MAX_VALUE);
        List<String> ans = new ArrayList<>();
        for (String word : A) {
            int[] charFreq = new int[26];
            for (int i = 0; i < word.length(); i++) {
                charFreq[word.charAt(i) - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                minFreq[j] = Math.min(charFreq[j], minFreq[j]);
            }
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < minFreq[i]; j++) {
                ans.add(String.valueOf((char)(i + 'a')));
            }
        }
        return ans;
    }
}
